package com.dna.everythingisbad.commands.utils;

import net.minecraft.command.CommandException;

public class InsufficientFundsException extends CommandException {
    public InsufficientFundsException(String message) {
        super(message, new Object());
    }
}
