package com.github.davinkevin.betmanager.repository.dsl;

import com.github.davinkevin.betmanager.entity.QMatch;
import com.mysema.query.types.expr.BooleanExpression;

import java.time.ZonedDateTime;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
public class MatchDSL {

    public static BooleanExpression withCompetitionId(Long competitionId) {
        return QMatch.match.competition.id.eq(competitionId);
    }
    public static BooleanExpression withDateBefore(ZonedDateTime date) {
        return QMatch.match.date.before(date);
    }
    public static BooleanExpression withDateAfter(ZonedDateTime date) {
        return QMatch.match.date.after(date);
    }

}
