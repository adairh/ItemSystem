package net.wdlvn.IS;

public enum Attributes {
    DAMAGE("DAMAGE"),
    MAGICAL_DAMAGE("MAGICAL_DAMAGE"),
    ARMOR("ARMOR"),
    MAGICAL_ARMOR("MAGICAL_ARMOR"),
    CRITICAL("CRITICAL"),
    CRITICAL_CHANCE("CRITICAL_CHANCE"),
    PIERCE("PIERCE"),
    MAGIC_PIERCE("MAGIC_PIERCE"),
    VAMPIRE("VAMPIRE"),
    VAMPIRE_CHANCE("VAMPIRE_CHANCE");

    private String basicName;

    private Attributes(String basicName) {
      this.basicName = basicName;
    }
    public String getName() {
      return this.basicName;
    }

}
