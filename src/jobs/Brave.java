package jobs;

import magic.IMagic;
import magic.NomalMagic;
import magic.Special;

public class Brave extends BasePlayer {

    IMagic nMagic = new NomalMagic();
    IMagic specialMagic = new Special();

    public Brave(String name) {
        super(name);
    }

    /**
     * 名前(name)からキャラクターに必要なパラメータを生成する
     */
    @Override
    protected void makeCharacter() {
        // 勇者のパラメータを名前から生成する
        this.hp = getNumber(0, 200) + 100;
        this.mp = getNumber(1, 30) + 10;
        this.str = getNumber(2, 40) + 10;
        this.def = getNumber(3, 60) + 20;
        this.luck = getNumber(4, 90) + 10;
        this.agi = getNumber(5, 40) + 30;
    }

    @Override
    public void attack(BasePlayer attacker, BasePlayer defender) {

        System.out.println(this.getName() + "の攻撃!");
        
        if (canSpecial()) {
            specialMagic.doMagic(attacker, defender);
            
        } else {
            nMagic.doMagic(attacker, defender);
        }

        powerUp();
    }

    public boolean canSpecial() {
        return this.getHp() <= 50 ? true : false;
    }

    private void powerUp() {
        System.out.println("勇者は成長する…。");
        int upStr = 10;
        this.powerEnhancement(upStr);
    }

}