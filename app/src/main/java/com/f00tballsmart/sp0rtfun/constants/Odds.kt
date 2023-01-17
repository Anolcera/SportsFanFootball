package com.f00tballsmart.sp0rtfun.constants

data class strongTeamWinOdds(
    val odds: Map<Int, Float> = mapOf(
        0 to 1.97f,
        1 to 1.8f,
        2 to 1.7f,
        3 to 1.6f,
        4 to 1.5f,
        5 to 1.4f,
        6 to 1.3f,
        7 to 1.2f,
        8 to 1.1f,
        9 to 1.1f
    )
)

data class weakTeamWinOdds(
    val odds: Map<Int, Float> = mapOf(
        0 to 1.97f,
        1 to 1.9f,
        2 to 2.0f,
        3 to 2.2f,
        4 to 2.4f,
        5 to 2.6f,
        6 to 2.8f,
        7 to 3.0f,
        8 to 3.5f,
        9 to 3.8f
    )
)

data class drawOdds(
    val odds: Map<Int, Float> = mapOf(
        0 to 2.3f,
        1 to 2.2f,
        2 to 2.1f,
        3 to 2.0f,
        4 to 1.9f,
        5 to 1.8f,
        6 to 1.7f,
        7 to 1.6f,
        8 to 1.5f,
        8 to 1.4f
    )
)

data class eitherWinOdds(
    val odds: Map<Int, Float> = mapOf(
        0 to 2.8f,
        1 to 2.6f,
        2 to 2.4f,
        3 to 2.2f,
        4 to 2.0f,
        5 to 1.8f,
        6 to 1.6f,
        7 to 1.4f,
        8 to 1.3f,
        9 to 1.2f
    )
)

data class strongTeamWinOrDrawOdds(
    val odds: Map<Int, Float> = mapOf(
        0 to 1.9f,
        1 to 1.8f,
        2 to 1.7f,
        3 to 1.6f,
        4 to 1.5f,
        5 to 1.4f,
        6 to 1.3f,
        7 to 1.2f,
        8 to 1.15f,
        9 to 1.1f
    )
)

data class weakTeamWinOrDrawOdds(
    val odds: Map<Int, Float> = mapOf(
        0 to 1.9f,
        1 to 2.0f,
        2 to 2.1f,
        3 to 2.2f,
        4 to 2.3f,
        5 to 2.4f,
        6 to 2.7f,
        7 to 3.0f,
        8 to 3.5f,
        9 to 4.0f
    )
)