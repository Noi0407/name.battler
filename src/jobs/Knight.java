package jobs;

import java.util.List;
import java.util.ArrayList;

import magic.IMagic;
import magic.NomalMagic;

import magic.Protection;
import magic.Fire;
import magic.FirstAid;

public class Knight extends BasePlayer {

    List<IMagic> magics = new ArrayList<>();

    IMagic nMagic = new NomalMagic();

    public Knight(String name) {
        super(name);
        setMagics(magics);
    }

    /**
     * 名前(name)からキャラクターに必要なパラメータを生成する
     */
    @Override
    protected void makeCharacter() {
        // 騎士のパラメータを名前から生成する
        this.hp = getNumber(0, 120) + 80;
        this.mp = getNumber(1, 30) + 20;
        this.str = getNumber(2, 30) + 40;
        this.def = getNumber(3, 30) + 30;
        this.luck = getNumber(4, 40) + 20;
        this.agi = getNumber(5, 20) + 30;
    }

    private void setMagics(List<IMagic> magics) {
        magics.add(new Protection());
        magics.add(new Fire());
        magics.add(new FirstAid());
    }

    @Override
    public void attack(BasePlayer attacker, BasePlayer defender) {

        System.out.println(this.getName() + "の攻撃!");

        if (canAllMagics()) {

            //作戦に従ってマジックを選択して使う
            operation.selectMagic(magics).doMagic(attacker, defender);
            
        }else{
            nMagic.doMagic(attacker, defender);
        }

    }

    private boolean canAllMagics() {
        return this.getMp() >= 10 ? true : false;
    }

}