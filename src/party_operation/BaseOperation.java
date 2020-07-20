package party_operation;

import java.util.List;
import java.util.Random;

import jobs.BasePlayer;
import magic.IMagic;

public class BaseOperation implements IOperation {

    Random random = new Random();

    protected OperationType operationType;

    public BaseOperation(){
        
        this.initOperation();
    }

    @Override
    public void initOperation() {

    }

    @Override
    public BasePlayer selectEnemy(Party targetParty) {

        BasePlayer defender = targetParty.getMembers().get(random.nextInt(targetParty.getMembers().size()));
        return defender;

    }

    @Override
    public IMagic selectMagic(List<IMagic> canMagic) {

        IMagic useMagic = canMagic.get(random.nextInt(canMagic.size()));
        return useMagic;

    }

    
    
}