package com.github.davinkevin.betmanager.repository.dsl;

import com.github.davinkevin.betmanager.entity.Match;
import com.github.davinkevin.betmanager.entity.QBet;
import com.github.davinkevin.betmanager.entity.QMatch;
import com.mysema.query.types.expr.BooleanExpression;

import java.util.List;

/**
 * Created by kevin on 15/08/15 for betmanager
 */
public class BetDSL {

    public static BooleanExpression withMatchId(Long id) {
        return QBet.bet.match.id.eq(id);
    }
    public static BooleanExpression withUserId(Long id) {
        return QBet.bet.user.id.eq(id);
    }
    public static BooleanExpression withCompetitionId(Long id) {
        return QBet.bet.match.competition.id.eq(id);
    }

    public static BooleanExpression withMatchIn(List<Match> matchs) {
        QMatch match = QBet.bet.match;
        return BooleanExpression.allOf(
                matchs.stream()
                .map(match::eq)
                .toArray(BooleanExpression[]::new)
        );
    }
}
