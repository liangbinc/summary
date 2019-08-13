package com.lbc.mo.variety;

public class StateControl {

    private static final boolean[][] STATEMAP = {
            // Normal TaskOver Lack QueuingToRunning AgentLost

            /* Normal */ {false, true, true, false, false},
           /* TaskOver */ {false, false, false, false, false},
            /* Lack */ {false, true, false, true, false},
           /* QueuingToRunning */ {true, true, false, false, false},
            /* AgentLost */ {false, false, false, false, false},
    };


    public static boolean isValidStateTransition(State current, State proposed) {
        boolean[] row = STATEMAP[current.getValue()];
        return row[proposed.getValue()];
    }

    public static void checkStateTransition(String name, State current, State proposed) {
        if (!isValidStateTransition(current, proposed)) {
            throw new ClassCastException(name + " cannot enter state " + proposed + " from state " + current);
        }
    }

    public static void main(String[] args) {
        Content content = new Content();
        content.setAppId("app_changlb_1562900929557");
        content.changeStateTo(new LackState(content));
//        content.changeStateTo(new NormalState(content));
        content.changeStateTo(new QueuingToRunningState(content));
        content.changeStateTo(new TaskOverState(content));
    }

}
