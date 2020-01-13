package com.ccs.professorlol.dto.lolinfo.champion;

import com.ccs.professorlol.dto.lolinfo.stat.StatResDto;
import com.ccs.professorlol.lolInfo.champion.Champion;
import com.ccs.professorlol.lolInfo.champion.Stat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChampionResDto {

    private String riotId;
    private String key;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<StatResDto> statResDtos;

    @Builder
    private ChampionResDto(String riotId, String key, String name, List<StatResDto> statResDtos) {
        this.riotId = riotId;
        this.key = key;
        this.name = name;
        this.statResDtos = statResDtos;
    }

    public static ChampionResDto from(Champion champion) {
        return ChampionResDto.builder()
                .key(champion.getKey())
                .name(champion.getName())
                .riotId(champion.getRiotId())
                .build();
    }

    public static ChampionResDto from(Champion champion, List<Stat> stats) {
        return ChampionResDto.builder()
                .key(champion.getKey())
                .name(champion.getName())
                .riotId(champion.getRiotId())
                .statResDtos(convertStat(stats))
                .build();
    }

    private static List<StatResDto> convertStat(List<Stat> stats) {
        if (ObjectUtils.isEmpty(stats)) {
            return new ArrayList<>();
        }
        return stats.stream()
                .map(StatResDto::from)
                .collect(toList());
    }
}
