package party_operation;

import jobs.BasePlayer;

public class ReduceStrongEnemy extends BaseOperation {

    @Override
    public void initOperation() {

        this.operationType = OperationType.REDUCE_STRONG_ENEMY;

    }

    @Override
    public BasePlayer selectTarget(Party targetParty) {

        BasePlayer defender = targetParty.getMembers().get(0);

        for (BasePlayer p : targetParty.getMembers()) {
            
            if (defender.getStr() < p.getStr())
                defender = p;
        }

        return defender;
    }

}
