package party_operation;

import jobs.BasePlayer;

public class ReduceLowHpEnemy extends BaseOperation {

    @Override
    public void initOperation() {
        this.operationType = OperationType.REDUCE_LOW_HP_ENEMY;
    }

    @Override
    public BasePlayer selectDefender(Party targetParty) {

        BasePlayer defender = targetParty.getMembers().get(0);

        for (BasePlayer p : targetParty.getMembers()) {
            
            if (defender.getHp() > p.getHp())
                defender = p;
        }

        return defender;
    }
}