package com.ccs.professorlol.patch.champion;

import com.ccs.professorlol.patch.skill.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class ChampionPatchHistoryTest {

    @Autowired
    PatchHistoryRepository patchHistoryRepository;

    @Autowired
    ChampionPatchHistoryRepository championPatchHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    static String championName = "라이즈";
    static String summary1 = "Q - 과부하 최고 스킬 레벨이 감소하고 보호막이 삭제되며 추가 피해량이 R - 공간 왜곡에 따라 증가합니다.";
    static String context1 = "라이즈로 최적의 플레이를 펼칠 경우 약점을 찾기가 너무나 힘듭니다. 또한 라이즈는 사전 구성된 팀에서 아주 강한 위력을 발휘합니다.";

    @Test
    public void 패치_히스토리가_부모클래스로저장시_ChampionPatchHistory_테이블로__만들어지는지_확인() {
        //given
        PatchHistory patchHistory = makePatchHistory();

        //when
        patchHistoryRepository.save(patchHistory);
        entityManager.clear();

        ChampionPatchHistory championPatchHistory = championPatchHistoryRepository.findAll().get(0);

        //then
        assertThat(championPatchHistory.getPatchVersion()).isEqualTo("1v");
        assertThat(championPatchHistory.getSummary()).isEqualTo(summary1);
        assertThat(championPatchHistory.getChampionName()).isEqualTo(championName);
        assertThat(championPatchHistory.getContext()).isEqualTo(context1);
        assertThat(championPatchHistory.getChampionAbilityHistories().size()).isEqualTo(3);
        assertThat(championPatchHistory.getChampionAbilityHistories().get(0).getAttribute()).isEqualTo("보호막");

    }


    @Test
    public void 패치_히스토리가_내클래스로저장시_ChampionPatchHistory_테이블로__만들어지는지_확인() {
        //given
        ChampionPatchHistory patchHistory = makeChampionPatchHistory();

        //when
        championPatchHistoryRepository.save(patchHistory);
        entityManager.clear();


        ChampionPatchHistory championPatchHistory = championPatchHistoryRepository.findAll().get(0);

        //then
        assertThat(championPatchHistory.getPatchVersion()).isEqualTo("1v");
        assertThat(championPatchHistory.getSummary()).isEqualTo(summary1);
        assertThat(championPatchHistory.getChampionName()).isEqualTo(championName);
        assertThat(championPatchHistory.getContext()).isEqualTo(context1);
        assertThat(championPatchHistory.getChampionAbilityHistories().size()).isEqualTo(3);
    }

    private PatchHistory makePatchHistory() {
        List<ChampionAbilityHistory> championAbilityHistories = makeAbilityList();

        PatchHistory patchHistory = ChampionPatchHistory.builder()
                .patchVersion("1v")
                .championAbilityHistories(championAbilityHistories)
                .championName(championName)
                .summary(summary1)
                .context(context1)
                .build();

        return patchHistory;
    }

    private ChampionPatchHistory makeChampionPatchHistory() {
        List<ChampionAbilityHistory> championAbilityHistories = makeAbilityList();

        ChampionPatchHistory patchHistory = ChampionPatchHistory.builder()
                .patchVersion("1v")
                .championAbilityHistories(championAbilityHistories)
                .championName(championName)
                .summary(summary1)
                .context(context1)
                .build();

        return patchHistory;
    }

    private List<ChampionAbilityHistory> makeAbilityList() {

        String attribute1 = "보호막";
        String removeContent1 = "이제 룬이 2개 방출되어도 라이즈에게 보호막을 씌우지 않습니다.";

        String attribute2 = "피해량";
        String changeContent1 = "60/85/110/135/160/185";
        String changeContent2 = "80/105/130/155/180";

        String attribute3 = "멀리 멀리 퍼져라";
        String newContent1 = "이제 첫 번째 대상 주변의 적에게 항상 전이 표식을 남깁니다.";

        List<ChampionAbilityHistory> championAbilityHistories = new ArrayList<>();

        ChampionAbilityHistory championAbilityHistory1 = RemoveAbility.builder()
                .attribute(attribute1)
                .removeContent(removeContent1)
                .skillType(SkillType.Q)
                .build();

        ChampionAbilityHistory championAbilityHistory2 = ChangeAbility.builder()
                .attribute(attribute2)
                .beforeContent(changeContent1)
                .afterContent(changeContent2)
                .skillType(SkillType.Q)
                .build();

        ChampionAbilityHistory championAbilityHistory3 = NewAbility.builder()
                .attribute(attribute3)
                .newContent(newContent1)
                .skillType(SkillType.E)
                .build();

        championAbilityHistories.add(championAbilityHistory1);
        championAbilityHistories.add(championAbilityHistory2);
        championAbilityHistories.add(championAbilityHistory3);

        return championAbilityHistories;
    }


}