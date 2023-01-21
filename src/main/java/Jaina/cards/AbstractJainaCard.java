package Jaina.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractJainaCard extends CustomCard {

    public AbstractJainaCard(String ID, boolean useTestArt, CardStrings cardStrings, int cost,
                             CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(ID, cardStrings.NAME, useTestArt ? getTestImgPath(type) : getImgPath(type, ID), cost,
                cardStrings.DESCRIPTION, type, color, rarity, target);
    }

    /**
     * 获取指定卡牌的图片路径
     * @param type 卡牌类型
     * @param id 完整卡牌id
     * @return 图片路径
     */
    private static String getImgPath(CardType type, String id) {
        String t;
        switch (type) {
            case ATTACK:
                t = "attack";
                break;
            case SKILL:
                t = "skill";
                break;
            case POWER:
                t = "power";
                break;
            case STATUS:
                t = "status";
                break;
            case CURSE:
                t = "curse";
                break;
            default:
                throw new IllegalArgumentException("Unexpect value: " + type);
        }
        return "Jaina/img/cards/" + t + "/" + id.substring(6) + ".png";
    }

    /**
     * 获取卡牌测试图片路径
     * @param type 卡牌类型
     * @return 测试图片路径
     */
    private static String getTestImgPath(CardType type) {
        String t;
        switch (type) {
            case ATTACK:
                t = "attack";
                break;
            case POWER:
                t = "power";
                break;
            case CURSE:
            case STATUS:
            case SKILL:
                t = "skill";
                break;
            default:
                throw new IllegalArgumentException("Unexpect value: " + type);
        }
        return "Jaina/img/cards/" + t + "/test32.png";
    }

    /**
     * 设置基础伤害值
     * @param damage 伤害值
     */
    protected void setDamage(int damage) {
        this.baseDamage = damage;
        this.damage = damage;
    }

    /**
     * 设置基础格挡值
     * @param block 格挡值
     */
    protected void setBlock(int block) {
        this.baseBlock = block;
        this.block = block;
    }

    /**
     * 设置基础特殊值
     * @param number 特殊值
     */
    protected void setMagicNumber(int number) {
        this.baseMagicNumber = number;
        this.magicNumber = number;
    }

    /**
     * 升级卡牌描述并更新描述
     * @param cardStrings 卡牌信息
     */
    protected void upgradeDescription(CardStrings cardStrings) {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    /**
     * 造成伤害
     * @param m 目标
     * @param ae 伤害效果
     */
    public void dealDamage(AbstractMonster m, AbstractGameAction.AttackEffect ae){
        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), ae));
    }

    /**
     * 造成AOE伤害
     * @param ae 伤害效果
     */
    public void dealAoeDamage(AbstractGameAction.AttackEffect ae) {
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage,
                this.damageTypeForTurn, ae));
    }

    /**
     * 获得格挡
     */
    public void gainBlock() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    /**
     * 获得一定量格挡
     * @param block 格挡值
     */
    public void gainBlock(int block) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, block));
    }

    /**
     * 抽牌
     * @param n 抽牌数量
     */
    public void drawCards(int n) {
        this.addToBot(new DrawCardAction(n));
    }

    /**
     * 给予玩家一层能力
     * @param power 能力
     */
    public void gainPower(AbstractPower power) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }

    /**
     * 给予目标生物一个能力
     * @param power 能力
     * @param creature 目标生物
     * @param amount 能力层数
     */
    public void givePower(AbstractPower power, AbstractCreature creature, int amount) {
        this.addToBot(new ApplyPowerAction(creature, AbstractDungeon.player, power, amount));
    }

    //重写了升级方法，升级效果写在limitedUpgrade中即可
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upp();
        }
    }

    //升级只需重写此方法
    public void upp() {}

}