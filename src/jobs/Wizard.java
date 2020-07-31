package jobs;

import magic.IMagic;
import magic.NomalMagic;
import magic.Thunder;
import magic.Fire;

import java.util.ArrayList;
import java.util.List;

public class Wizard extends BasePlayer {

    IMagic nMagic = new NomalMagic();

    List<IMagic> someMagcs = new ArrayList<>();
    List<IMagic> allMagics = new ArrayList<>();

    public Wizard(String name) {
        
        super(name);

        setMagics(someMagcs, false);
        setMagics(allMagics, true);
    }

    /**
     * 名前(name)からキャラクターに必要なパラメータを生成する
     */
    @Override
    protected void makeCharacter() {
        // 僧侶のパラメータを名前から生成する
        this.hp = getNumber(0, 100) + 50;
        this.mp = getNumber(1, 50) + 30;
        this.str = getNumber(2, 49) + 1;
        this.def = getNumber(3, 49) + 1;
        this.luck = getNumber(4, 99) + 1;
        this.agi = getNumber(5, 40) + 20;
    }

    private void setMagics(List<IMagic> magics, boolean setAllMagic) {
        magics.add(new Fire());

        if (setAllMagic)
        magics.add(new Thunder());
    }

    @Override
    public void attack(BasePlayer attacker, BasePlayer defender) {
        System.out.println(this.getName() + "の攻撃!");

        if (canAllMagics()) {
            operation.selectMagic(allMagics).doMagic(attacker, defender);

        } else if (canSomeMagics()) {
            operation.selectMagic(someMagcs).doMagic(attacker, defender);

        } else {
            nMagic.doMagic(attacker, defender);
        }
    }

    private boolean canAllMagics() {
        return this.getMp() >= 20 ? true : false;
    }

    private boolean canSomeMagics() {
        return this.getMp() >= 10 ? true : false;
    }

}