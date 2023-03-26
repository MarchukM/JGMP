module jmp.cloud.service.impl {
    requires jmp.dto;
    requires transitive jmp.service.api;
    exports org.jmp.bank.cloud.service.impl;
    provides org.jmp.bank.cloud.service.IBankService with
            org.jmp.bank.cloud.service.impl.BankService;
}