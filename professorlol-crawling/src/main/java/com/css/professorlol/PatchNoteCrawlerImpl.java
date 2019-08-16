package com.css.professorlol;

import com.css.professorlol.domain.champion.ChampionFactory;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.css.professorlol.jsoupUtil.DocumentUtil.convertFromUrlConnection;

public class PatchNoteCrawlerImpl implements PatchNoteCrawler {

    private static String BASE_URL_FRONT = "https://www.leagueoflegends.co.kr/?m=news&cate=update&mod=view&schwrd=&p=2&idx=";
    private static String BASE_URL_BACK = "#.XUWOZugzZPZ";
//    262762

    @Override
    public ChampionFactory getChampionPatchById(Long id) throws IOException {
        String completeURL = BASE_URL_FRONT + id + BASE_URL_BACK;
        Document document = convertFromUrlConnection(completeURL);
        Elements select = document.select(".content-border .patch-change-block");
        return new ChampionFactory(select);
    }
}
