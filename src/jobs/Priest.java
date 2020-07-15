package jobs;

import magic.IMagic;
import magic.NomalMagic;
import magic.Paralysis;
import magic.Poison;
import magic.Heal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Priest extends BasePlayer {

    List<IMagic> someMagics = new ArrayList<IMagic>();
    List<IMagic> allMagics = new ArrayList<IMagic>();
    IMagic nMagic = new NomalMagic();

    Random random = new Random();

    public Priest(String name) {
        
        super(name);

        setMagics(someMagics, false);
        setMagics(allMagics, true);
    }

    /**
     * 名前(name)からキャラクターに必要なパラメータを生成する
     */
    @Override
    protected void makeCharacter() {
        // 僧侶のパラメータを名前から生成する
        this.hp = getNumber(0, 120) + 80;
        this.mp = getNumber(1, 30) + 20;
        this.str = getNumber(2, 60) + 10;
        this.def = getNumber(3, 60) + 10;
        this.luck = getNumber(4, 99) + 1;
        this.agi = getNumber(5, 40) + 20;
    }

    protected void setMagics(List<IMagic> magics, boolean setAllMagic) {
        magics.add(new Poison());
        magics.add(new Paralysis());

        if (setAllMagic)
        magics.add(new Heal());
    }

    /**
     * 対象プレイヤーに攻撃を行う
     * 
     * @param defender : 対象プレイヤー
     */
    @Override
    public void attack(BasePlayer attacker, BasePlayer defender) {
        System.out.println(this.getName() + "の攻撃!");

        if (canAllMagics()) {
            selectDoMagic(allMagics, attacker, defender);
        } else if (canSomeMagics()) {
            selectDoMagic(someMagics, attacker, defender);
        } else {
            // 通常攻撃を行う
            nMagic.doMagic(attacker, defender);
        }
    }

    protected boolean canAllMagics() {
        return (this.getMp() >= 20) && (this.getMaxHp() >= this.getHp() + 50) ? true : false;
    }

    protected boolean canSomeMagics() {
        return this.getMp() >= 10 ? true : false;
    }

    protected void selectDoMagic(List<IMagic> magics, BasePlayer attacker, BasePlayer defender) {
        int selectIndex = random.nextInt(magics.size());
        magics.get(selectIndex).doMagic(attacker, defender);
    }

}