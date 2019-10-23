package com.dna.everythingisbad.damagesource;

import cofh.core.util.helpers.DamageHelper;
import net.minecraft.util.DamageSource;

public class ModDamageSources {
    public static final DamageSourceHeartAttack HEART_ATTACK = new DamageSourceHeartAttack();

    public static class DamageSourceHeartAttack extends DamageSource {

        protected DamageSourceHeartAttack() {

            super("heart_attack");
            this.setDamageBypassesArmor();
        }
    }
}
