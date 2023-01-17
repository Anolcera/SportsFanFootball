package com.f00tballsmart.sp0rtfun.presentation.home_screen.game_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.f00tballsmart.sp0rtfun.R
import com.f00tballsmart.sp0rtfun.constants.*
import com.f00tballsmart.sp0rtfun.data.local.GameEntity
import com.f00tballsmart.sp0rtfun.data.local.PlayerTeam
import com.f00tballsmart.sp0rtfun.data.mapper.toGame
import com.f00tballsmart.sp0rtfun.data.mapper.toTeam
import com.f00tballsmart.sp0rtfun.data.repository.PreferencesRepository
import com.f00tballsmart.sp0rtfun.data.repository.TeamsRepositoryImpl
import com.f00tballsmart.sp0rtfun.domain.model.Game
import com.f00tballsmart.sp0rtfun.domain.model.Team
import com.f00tballsmart.sp0rtfun.util.GameResult
import com.f00tballsmart.sp0rtfun.util.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val teamsRepositoryImpl: TeamsRepositoryImpl,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {


    //private val preferencesRepository = PreferencesRepository.get()

    private val playerTeamName = checkNotNull(savedStateHandle.get<String>(PLAYER_TEAM_NAME))
    private val opponentTeamName = checkNotNull(savedStateHandle.get<String>(OPPONENT_TEAM_NAME))
    private val bet: String = checkNotNull(savedStateHandle.get<String>(BET_ARGUMENT_KEY))
    private val currentRound: Int = checkNotNull(savedStateHandle.get<Int>(ROUND_ARGUMENT_KEY))
    private val isPlayerAtHome: Boolean = checkNotNull(savedStateHandle.get<Boolean>(IS_PLAYER_AT_HOME))

    private val teamListLogos: MutableList<Int> = List(18){0}.toMutableList()

    private lateinit var playerTeam: PlayerTeam
    private lateinit var opponentTeam: Team
    private lateinit var tournamentGames: MutableList<Game>
    private lateinit var currentRoundGames: List<Game>
    private lateinit var teams: List<Team>
    private lateinit var currentGame: Game

    var state by mutableStateOf(GameScreenState())
    val teamList: MutableList<String> = mutableListOf("Ajax", "AZ", "SC Cambuur", "FC Emmen", "Excelsior", "Feyenoord", "Fortuna Sittard",
        "Go Ahead Eagles", "FC Groningen", "Ajax", "AZ", "SC Cambuur", "FC Emmen", "Excelsior", "Feyenoord", "Fortuna Sittard",
        "Go Ahead Eagles", "FC Groningen")

    var chosenTeam: String = ""
    var currentTeam: String = ""
    var chosenTeamIndex: Int = -1
    var openedCards: Int = 0

    var _imagesState = mutableStateListOf<Int>()
    val imagesState : List<Int>
        get() = _imagesState

    init {
        viewModelScope.launch {
            teamList.shuffle()
            teamList.forEachIndexed { index, s ->
                teamListLogos[index] = TeamLogosSquare().logos[s]!!
            }
        }

        viewModelScope.launch{
            _imagesState.addAll(MutableList(18) { R.drawable.card_back })
        }

        viewModelScope.launch {
            preferencesRepository.storedPlayerTeam.collectLatest {
                try {
                    val playerTeamFromJson = Gson().fromJson(it, PlayerTeam::class.java)
                    //state = state.copy(playerTeam = playerTeam)
                    playerTeam = playerTeamFromJson

                } catch (ex: Exception) {

                }
            }
        }

        viewModelScope.launch {
            try {
//                state = state.copy(
//                    opponentTeam = teamsRepositoryImpl.getFootballTeam(opponentTeamName)
//                )
                opponentTeam = teamsRepositoryImpl.getFootballTeam(opponentTeamName)
            }catch (ex: Exception) {

            }
        }

        viewModelScope.launch {
           teamsRepositoryImpl.getFootballTeams().collectLatest { result ->
               when(result){
                   is Resource.Success -> {
                       result.data?.let {
//                           state = state.copy(
//                               teams = it
//                           )
                           teams = it
                       }
                   }

                   is Resource.Loading -> {}
                   is Resource.Error -> {}
               }
           }
        }

        viewModelScope.launch {
            preferencesRepository.storedGames.collectLatest {
                try {
                    val type = object : TypeToken<List<GameEntity>>() {}.type
                    val gameEntities = Gson().fromJson<List<GameEntity>>(it, type)

                    val games = gameEntities.map {
                        it.toGame()
                    }


                    tournamentGames = games as MutableList<Game>
                    currentRoundGames = games.asSequence().filter {
                        it.roundNumber == currentRound
                    }.toList()

                    currentGame = currentRoundGames.asSequence().filter {
                        it.homeTeam.name == playerTeamName || it.awayTeam.name == playerTeamName
                    }.toList()[0]

//                    state = state.copy(
//                        tournamentGames = games as MutableList<Game>,
//                        currentRoundGames = games.asSequence().filter {
//                            it.roundNumber == currentRound
//                        }.toList() as MutableList<Game>
//                    )

                } catch (ex: Exception) {
                    Log.e("Shplinti", "Failed to get stored games", ex)
                }
            }
        }
    }

    fun openCurrentIndexCard(currentIndex: Int){
        _imagesState[currentIndex] = teamListLogos[currentIndex]
    }

    fun closeIndexedCards(currentIndex: Int) {
        _imagesState[chosenTeamIndex] = R.drawable.card_back
        _imagesState[currentIndex] = R.drawable.card_back
    }

    fun updateTimer(ticks: Int) {
        state = state.copy(
            timer = ticks
        )
    }

    fun updateTimeInfo() {
        state = state.copy(
            timeInfo = "Draw: 60"
        )
    }

    suspend fun updateCurrentGameStats(result: GameResult, timeFinished: Int){

        if (!this::currentGame.isInitialized) throw ExceptionInInitializerError("Lateinit vars couldn't finish initializations")

        simulateOtherTeamResults()

        updatePlayerAndOpponentTeams(
            bet = checkNotNull(BetsMap[bet]),
            result = result,
            timeFinished = timeFinished
        )

        updateScore(result)
    }

    private suspend fun updatePlayerAndOpponentTeams(
        bet: Bets,
        result: GameResult,
        timeFinished: Int,
    ){

        playerTeam.apply {
            this.averageTime = (this.averageTime + timeFinished) / currentRound
            this.playedRound += 1
        }
        when(result){
            GameResult.Draw -> {
                opponentTeam.draw += 1
                opponentTeam.point += 1

                playerTeam.apply {
                    this.draw = this.draw + 1
                    this.point += 1
                }
                updateDraw(bet)
            }
            GameResult.Lose -> {
                playerTeam.apply {
                    this.lose = this.lose + 1
                }
                opponentTeam.win += 1
                opponentTeam.point += 3
                if(isPlayerAtHome) updatePlayerHomeLose(bet) else updatePlayerAwayLose(bet)
            }
            GameResult.Win -> {
                playerTeam.apply {
                    this.win = this.win + 1
                    this.point += 3
                }
                opponentTeam.lose += 1
                if(isPlayerAtHome) updatePlayerHomeWin(bet) else updatePlayerAwayWin(bet)
            }
        }

        val playerTeamToJson = Gson().toJson(playerTeam)
        storeData(playerTeamToJson)

    }

    private suspend fun storeData(
        playerTeamToJson: String,
    ){
        try {
            preferencesRepository.deletePlayerTeam()
            preferencesRepository.updatePlayerTeam(playerTeamToJson)

            teamsRepositoryImpl.updateTeam(playerTeam.toTeam())
            teamsRepositoryImpl.updateTeam(opponentTeam)
        }catch (ex: Exception){
            
        }
    }


    private suspend fun updateScore(
        result: GameResult
    ){
        val currentGameIndex = tournamentGames.indexOf(currentGame)
        //val newTournament : MutableList<Game> = state.tournamentGames.toMutableList() // as MutableList<Game>

        val winnerScore = Random.nextInt(1,4)
        val loserScore = Random.nextInt(0, winnerScore)

        when(result){
            is GameResult.Draw ->{
                val drawScore = Random.nextInt(0,4)
                currentGame.homeScore = drawScore
                currentGame.awayScore = drawScore
            }

            is GameResult.Win ->{
                if (isPlayerAtHome){
                    currentGame.homeScore = winnerScore
                    currentGame.awayScore = loserScore
                }else{
                    currentGame.homeScore = loserScore
                    currentGame.awayScore = winnerScore
                }
            }

            is GameResult.Lose ->{
                if (isPlayerAtHome){
                    currentGame.homeScore = loserScore
                    currentGame.awayScore = winnerScore
                }else{
                    currentGame.homeScore = winnerScore
                    currentGame.awayScore = loserScore
                }
            }
        }

        tournamentGames[currentGameIndex] = currentGame

        val tournamentToJson: String = Gson().toJson(tournamentGames)

        preferencesRepository.deleteTournament()
        preferencesRepository.updateGames(tournamentToJson)
    }

    private fun simulateOtherTeamResults(){
       viewModelScope.launch {
           currentRoundGames.map {
               simulateGame(it)
           }

           currentRoundGames.forEach { game ->
               if(game != currentGame){
                   val homeTeam : Team? = teams.find {
                       it.name == game.homeTeam.name
                   }
                   val awayTeam : Team? = teams.find{
                       it.name == game.awayTeam.name
                   }

                   homeTeam?.let {
                       it.win += game.homeTeam.win
                       it.draw += game.homeTeam.draw
                       it.lose += game.homeTeam.lose
                       it.point += game.homeTeam.point
                       teamsRepositoryImpl.updateTeam(it)
                   }

                   awayTeam?.let {
                       it.win += game.awayTeam.win
                       it.draw += game.awayTeam.draw
                       it.lose += game.awayTeam.lose
                       it.point += game.awayTeam.point
                       teamsRepositoryImpl.updateTeam(it)
                   }
               }
           }
       }
    }

    private fun simulateGame(game: Game) : Game{

        val homeTeamFightingSpirit = Random.nextInt(0, game.homeTeam.strength)
        val awayTeamFightingSpirit = Random.nextInt(0, game.awayTeam.strength)
        val match = homeTeamFightingSpirit - awayTeamFightingSpirit

        if (kotlin.math.abs(match) <= 1){
            game.homeTeam.updateDraw()
            game.awayTeam.updateDraw()
        }else if(match < 0){
            game.homeTeam.updateLose()
            game.awayTeam.updateWin()
        }else{
            game.awayTeam.updateLose()
            game.homeTeam.updateWin()
        }
        return game
    }

    private fun updatePlayerHomeWin(bet: Bets){
        if(bet == Bets.HomeTeamWin || bet == Bets.HomeTeamWinOrDraw || bet == Bets.EitherTeamWin){
            playerTeam.apply {
                this.winOdds = this.winOdds + 1
            }
        }else{
            playerTeam.apply {
                this.loseOdds = this.loseOdds + 1
            }
        }
    }

    private fun updatePlayerAwayWin(bet: Bets){
        if(bet == Bets.AwayTeamWin || bet == Bets.AwayTeamWinOrDraw || bet == Bets.EitherTeamWin){
            playerTeam.apply {
                this.winOdds = this.winOdds + 1
            }
        }else{
            playerTeam.apply {
                this.loseOdds = this.loseOdds + 1
            }
        }
    }

    private fun Team.updateDraw(){
        draw = 1
        lose = 0
        win = 0
        point = 1

    }

    private fun Team.updateLose(){
        draw = 0
        lose = 1
        win = 0
        point = 0

    }

    private fun Team.updateWin(){
        draw = 0
        lose = 0
        win = 1
        point = 3

    }

    private fun updatePlayerHomeLose(bet: Bets){
        updatePlayerAwayWin(bet)
    }

    private fun updatePlayerAwayLose(bet: Bets){
        updatePlayerHomeWin(bet)
    }

    private fun updateDraw(bet: Bets){
        if(bet == Bets.HomeTeamWinOrDraw || bet == Bets.AwayTeamWinOrDraw || bet == Bets.Draw){
            playerTeam.apply {
                this.winOdds = this.winOdds + 1
            }
        }else{
            playerTeam.apply {
                this.loseOdds = this.loseOdds + 1
            }
        }
    }

}