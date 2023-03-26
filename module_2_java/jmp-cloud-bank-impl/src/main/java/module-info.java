module jmp.cloud.bank.impl {
    requires jmp.dto;
    requires transitive jmp.bank.api;
    exports org.jmp.bank.cloud.impl;
    provides org.jmp.bank.api.IBank with
            org.jmp.bank.cloud.impl.Bank;
}