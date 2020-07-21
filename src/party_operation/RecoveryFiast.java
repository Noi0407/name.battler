package party_operation;

import java.util.List;

import magic.IMagic;
import magic.RecoveryHp;

public class RecoveryFiast extends BaseOperation {

    @Override
    public void initOperation() {
        this.operationType = OperationType.RECOVERY_FIAST;
    }

    // 回復魔法があれば使う、なかったらランダムに選ぶ
    @Override
    public IMagic selectMagic(List<IMagic> canMagic) {

        for (IMagic m : canMagic) {
            
            if (m instanceof RecoveryHp)
                return m;
        }

        return canMagic.get(random.nextInt(canMagic.size()));
    }
}