package party_operation;

import jobs.BasePlayer;

public class ReduceLowDefEnemy extends BaseOperation {

    @Override
    public void initOperation() {
        this.operationType = OperationType.REDUCE_LOW_DEF_ENEMY;
    }

    @Override
    public BasePlayer selectDefender(Party targetParty) {

        BasePlayer defender = targetParty.getMembers().get(0);

        for (BasePlayer p : targetParty.getMembers()) {

            if (defender.getDef() > p.getDef())
                defender = p;
        }

        return defender;
    }
}