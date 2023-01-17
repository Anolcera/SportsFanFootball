package com.f00tballsmart.sp0rtfun.util

//sealed class GameResult(result: String){
//    object Win : GameResult(result = "win")
//    object Draw : GameResult(result = "draw")
//    object Lose : GameResult(result = "lose")
//}

sealed class GameResult(){
    object Win : GameResult()
    object Draw : GameResult()
    object Lose : GameResult()
}
