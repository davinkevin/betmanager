package com.github.davinkevin.betmanager.repository;

import com.github.davinkevin.betmanager.entity.QBet;
import com.mysema.query.types.expr.BooleanExpression;

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
}
