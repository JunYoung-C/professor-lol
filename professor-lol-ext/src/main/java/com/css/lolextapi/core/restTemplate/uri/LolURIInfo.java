package com.css.lolextapi.core.restTemplate.uri;

//상수클래스 ㄴㄴ
public class LolURIInfo {

    //yml 에서 받아오게
    public static final String HTTPS = "https";

    public static final String HOST = "kr.api.riotgames.com";

    //흩어지고
    public static final String SUMMONER_BY_NAME = "/lol/summoner/v4/summoners/by-name/{summonerName}";
    public static final String LEAGUE_ENTRY_BY_SUMMONER = "/lol/league/v4/entries/by-summoner/{encryptedSummonerId}";
    public static final String MATCH_LIST_BY_ACCOUNT = "/lol/match/v4/matchlists/by-account/{encryptedAccountId}";

}