package jaina.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;


public class ShiftingScroll extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("ShiftingScroll");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -2;
    private AbstractJainaCard shiftCard;

    public ShiftingScroll() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.NONE);
        this.tags.add(JainaEnums.CardTags.SHIFT);
        this.selfRetain = true;
    }

    @Override
    public void upp() {
        // 战斗中升级本体
        if (shiftCard != null) {
            shiftCard.upp();
            this.upgraded = false;
        } else {
            // 战斗外升级加固有
            this.isInnate = true;
            upgradeDescription(CARD_STRINGS);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = Color.PURPLE.cpy();
    }

    @Override
    public void atTurnStart() {
        shiftCard = (AbstractJainaCard) IHelper.generateRandomJainaCards(1, true, true, true, false, false).get(0);
        if (upgraded) {
            shiftCard.upp();
        }

        this.type = shiftCard.type;
        this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[0] + shiftCard.name;
        this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[1] + shiftCard.rawDescription;
        this.cost = this.costForTurn = shiftCard.cost;
        this.portrait = shiftCard.portrait;
        this.rarity = shiftCard.rarity;
        this.target = shiftCard.target;
        this.tags = shiftCard.tags;
        this.tags.add(JainaEnums.CardTags.SHIFT);

        this.exhaust = shiftCard.exhaust;
        this.isEthereal = shiftCard.isEthereal;
        this.cardsToPreview = shiftCard.cardsToPreview;

        setDamage(shiftCard.baseDamage);
        setBlock(shiftCard.baseBlock);
        setMagicNumber(shiftCard.baseMagicNumber);

        this.initializeTitle();
        this.initializeDescription();
        this.update();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        shiftCard.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShiftingScroll();
    }

}
