package App.Domain.Cards.UpgradeCards.Permenant;

import App.Domain.Cards.UpgradeCards.UpgradeCard;
import App.Domain.Enums.PermanentUpgradeCardEnum;

public class PermanentUpgradeCard extends UpgradeCard {


    private final PermanentUpgradeCardEnum upgradeCardEnum;
    public PermanentUpgradeCard(boolean isPermanent, int cost, PermanentUpgradeCardEnum upgradeCardEnum) {
        super(isPermanent, cost);
        this.upgradeCardEnum = upgradeCardEnum;
    }
}
