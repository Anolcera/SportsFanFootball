package com.f00tballsmart.sp0rtfun.constants

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.f00tballsmart.sp0rtfun.R

enum class TeamDetails(
    @StringRes val teamName: Int,
    @StringRes val city: Int,
    @StringRes val stadium: Int
) {
    Ajax(
        teamName = R.string.ajax_name,
        city = R.string.amsterdam_city,
        stadium = R.string.ajax_stadium
    ),
    AZ(
        teamName = R.string.az_name,
        city = R.string.alkmaar_city,
        stadium = R.string.az_stadium
    ),
    Cambuur(
        teamName = R.string.cambuur_name,
        city = R.string.leeuwarden_city,
        stadium = R.string.cambuur_stadium
    ),
    Emmen(
        teamName = R.string.emmen_name,
        city = R.string.emmen_city,
        stadium = R.string.emmen_stadium
    ),
    Excelsior(
        teamName = R.string.excelsior_name,
        city = R.string.rotterdam_city,
        stadium = R.string.excelsior_stadium
    ),
    Feyenoord(
        teamName = R.string.feynoord_name,
        city = R.string.rotterdam_city,
        stadium = R.string.feynoord_stadium
    ),
    FortunaSittard(
        teamName = R.string.fortuna_sittard_name,
        city = R.string.sittard_city,
        stadium = R.string.fortuna_sittard_stadium
    ),
    GoAheadEagles(
        teamName = R.string.go_ahead_eagles_name,
        city = R.string.deventer_city,
        stadium = R.string.go_ahead_eagles_stadium
    ),
    Groningen(
        teamName = R.string.groningen_name,
        city = R.string.groningen_city,
        stadium = R.string.groningen_stadium
    ),
    Heerenveen(
        teamName = R.string.heerenveen_name,
        city = R.string.heerenveen_city,
        stadium = R.string.heerenveen_stadium
    ),
    Nijmegen(
        teamName = R.string.nec_nijmegen_name,
        city = R.string.nijmeg_city,
        stadium = R.string.nec_nijmegen_stadium
    ),
    PSV(
        teamName = R.string.psv_eindhoven_name,
        city = R.string.eindhoven_city,
        stadium = R.string.psv_eindhoven_stadium
    ),
    Waalwijk(
        teamName = R.string.rkc_waalwijk_name,
        city = R.string.waalwijk_city,
        stadium = R.string.rkc_waalwijk_stadium
    ),
    SpartaRotterdam(
        teamName = R.string.sparta_rotterdam_name,
        city = R.string.rotterdam_city,
        stadium = R.string.sparta_rotterdam_stadium
    ),
    Twente(
        teamName = R.string.twente_name,
        city = R.string.enschede_city,
        stadium = R.string.twente_stadium
    ),
    Utrecht(
        teamName = R.string.utrecht_name,
        city = R.string.utrecht_city,
        stadium = R.string.utrecht_stadium
    ),
    Vitesse(
        teamName = R.string.vitesse_name,
        city = R.string.arnhem_city,
        stadium = R.string.vitesse_stadium
    ),
    Volendam(
        teamName = R.string.volendam_name,
        city = R.string.volendam_city,
        stadium = R.string.volendam_stadium
    ),
}