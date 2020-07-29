package jobs;

import java.math.BigInteger;
import java.security.MessageDigest;

import party_operation.BaseOperation;
import party_operation.Party;

// プレイヤークラス(各種ジョブの基底クラス)
public class BasePlayer {

	//作戦
	BaseOperation operation;

	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name;
	// HP
	protected int hp;
	// MP
	protected int mp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;
	// 運
	protected int luck;
	// 素早さ
	protected int agi;

	// 最大HP
	protected int maxHp;
	// 毒になった残りターン数
	protected int poisonTurnNum;
	// 麻痺になった残りターン数
	protected int paralysisTurnNum;

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 * 
	 * @param name : プレイヤー名
	 */
	public BasePlayer(String name) {
		this.name = name;

		// キャラクターのパラメータ生成
		makeCharacter();
		this.maxHp = this.getHp();
	}

	public void setOperation(BaseOperation partyOperation){
		this.operation = partyOperation;
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * プレイヤー名を取得する
	 * 
	 * @return プレイヤー名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 現在HPを取得する
	 * 
	 * @return 現在HP
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * 現在MPを取得する
	 * 
	 * @return 現在MP
	 */
	public int getMp() {
		return this.mp;
	}

	/**
	 * 攻撃力を取得する
	 * 
	 * @return 攻撃力
	 */
	public int getStr() {
		return this.str;
	}

	/**
	 * 防御力を取得する
	 * 
	 * @return 防御力
	 */
	public int getDef() {
		return this.def;
	}

	/**
	 * 運を取得する
	 * 
	 * @return 運
	 */
	public int getLuck() {
		return this.luck;
	}

	/**
	 * 素早さを取得する
	 * 
	 * @return 素早さ
	 */
	public int getAgi() {
		return this.agi;
	}

	/**
	 * 最大HPを取得する
	 * 
	 * @return 最大HP
	 */
	public int getMaxHp() {
		return this.maxHp;
	}

	/**
	 * @return 毒の残りターン数
	 */
	public int getRemainingPoison() {
		return this.poisonTurnNum;
	}

	/**
	 * @return まひの残りターン数
	 */
	public int getRemainingParalysis() {
		return this.paralysisTurnNum;
	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void makeCharacter() {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 * 
	 * @param index : 何番目の数値を取り出すか
	 * @param max   : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int getNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			// ハッシュ値から指定された位置の文字列を取り出す（２文字分）
			String hex = digest.substring(index * 2, index * 2 + 2);

			// 取り出した文字列（16進数）を数値に変換する
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			// エラー
			e.printStackTrace();
		}
		return 0;
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 現在のステータスを System.out で表示する
	 */
	public void PrintStatus() {
		System.out.printf("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : luck=%3d : agi=%3d)\n", this.getName(),
				this.getHp(), this.getMp(), this.getStr(), this.getDef(), this.getLuck(), this.getAgi());
	}

	// 麻痺になる
	public void beParalysis(int paralysisTurn) {
		System.out.println(this.getName() + "は麻痺になった!");
		this.paralysisTurnNum += paralysisTurn;
	}

	// 行動できる(麻痺になっていたらしびれて動けない)
	public boolean canAct() {
		if (this.getRemainingParalysis() <= 0)
			return true;

		System.out.println(this.getName() + "は痺れて動けない…。");
		this.paralysisTurnNum--;
		return false;
	}

	// 毒になる
	public void bePoisonus(int poisonTurn) {
		System.out.println(this.getName() + "は毒になった!");
		this.poisonTurnNum += poisonTurn;
	}

	public boolean isPoison() {
		return this.poisonTurnNum > 0 ? true : false;
	}

	public void receivePoisonEffect() {
		int poisonDamage = 20;
		System.out.println(this.getName() + "に毒の症状が現れた!");
		this.receiveDamage(poisonDamage);
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * 
	 * @param defender : 対象プレイヤー
	 */
	public void attack(BasePlayer attacker, BasePlayer defender) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 * 
	 * @param target : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	public int calcDamage(BasePlayer target) {
		
		int damage = getStr() - target.getDef();
		
		return  damage < 0 ? 0 : damage;
	}

	/**
	 * ターゲットになるディフェンダーを決める
	 * @param defenseParty
	 * @return defender
	 */
	public BasePlayer decideDefender(Party defenseParty){
		BasePlayer defender = operation.selectDefender(defenseParty);
        return defender;
	}

	/**
	 * ダメージを受ける
	 * 
	 * @param damage : ダメージ値
	 */
	public void receiveDamage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.getHp() - damage, 0);

		damageMsg(damage);

		if (isDied()) {
			diedMsg();
		}
	}

	protected void damageMsg(int damage) {
		if (notDamage(damage)) {
			System.out.println("攻撃はミスだ。");
		} else {
			System.out.println(this.getName() + "に" + damage + "のダメージ!");
		}
	}

	protected boolean notDamage(int damage) {
		return damage <= 0 ? true : false;
	}

	protected boolean isDied() {
		return this.getHp() <= 0 ? true : false;
	}

	protected void diedMsg() {
		System.out.println(this.getName() + "は力尽きた・・・。");
	}

	public void downMp(int useMp) {
		this.mp = Math.max(this.getMp() - useMp, 0);
	}

	public void recoveryHp(int upHp) {

		int recoveryPoint = Math.min(upHp, this.getMaxHp() - this.getHp());
		this.hp = Math.min(this.getMaxHp(), this.getHp() + recoveryPoint);

		System.out.println(this.getName() + "は" + recoveryPoint + "回復した。");

	}

	public void defenseEnhancement(int upDef) {
		System.out.println(this.getName() + "の防御力が" + upDef + "アップ!");
		this.def += upDef;
	}

	public void powerEnhancement(int upStr){
		System.out.println(this.getName()+"の攻撃力が"+upStr+"アップ!");
		this.str += upStr;
	}

}