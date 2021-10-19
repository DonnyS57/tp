package donnafin.model.person;

import static donnafin.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import donnafin.commons.core.types.Money;
import donnafin.logic.parser.ParserUtil;
import donnafin.logic.parser.exceptions.ParseException;
import donnafin.ui.AttributeTable;

/**
 * Represents a Person's assets in DonnaFin.
 */
public class Asset implements Attribute {

    public static final String MESSAGE_CONSTRAINTS = "Insert asset constraint here";
    public static final String VALIDATION_REGEX = "[\\s\\S]*";
    public static final AttributeTable.TableConfig<Asset> TABLE_CONFIG = new AttributeTable.TableConfig<>(
        "Assets",
        List.of(
                new AttributeTable.ColumnConfig("Asset Name", "name", 300),
                new AttributeTable.ColumnConfig("Type", "type", 100),
                new AttributeTable.ColumnConfig("Value", "valueToString", 100),
                new AttributeTable.ColumnConfig("Remarks", "remarks", 100)
        ),
        assetCol -> {
            Money acc = new Money(0);
            try {
                for (Asset asset : assetCol) {
                    Money commission = asset.getValue();
                    acc = Money.add(acc, commission);
                }
            } catch (Money.MoneyException e) {
                return "-";
            }
            return acc.toString();
        }
    );
    private final String name;
    private final String type;
    private final Money value;
    private final String remarks;

    /**
     * Constructs a {@code Asset}.
     *
     * @param name A valid Asset name.
     * @param type An Asset type.
     * @param value An Asset's worth.
     * @param remarks A remark on Asset.
     */
    public Asset(String name, String type, String value, String remarks) {
        requireAllNonNull(name, type, value, remarks);
        this.name = name;
        this.type = type;
        try {
            this.value = ParserUtil.parseMoney(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException(Policy.MESSAGE_CONSTRAINTS);
        }
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Asset{"
                + "name='" + getName() + '\''
                + ", type='" + getType() + '\''
                + ", value='" + getValue() + '\''
                + ", remarks='" + getRemarks() + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Asset)) {
            return false;
        }

        Asset asset = (Asset) o;
        return getName().equals(asset.getName())
                && getType().equals(asset.getType())
                && getValue().equals(asset.getValue())
                && getRemarks().equals(asset.getRemarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), getValue(), getRemarks());
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Money getValue() {
        return value;
    }

    public String getValueToString() {
        return value.toString();
    }

    public String getRemarks() {
        return remarks;
    }
}
