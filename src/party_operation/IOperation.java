package party_operation;

import java.util.List;

import jobs.BasePlayer;
import magic.IMagic;

public interface IOperation {
    void initOperation();

    BasePlayer selectDefender(Party targetParty);

    IMagic selectMagic(List<IMagic> canMagic);
}