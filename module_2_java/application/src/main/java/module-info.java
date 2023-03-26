module application {
    requires jmp.dto;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;

    uses org.jmp.bank.api.IBank;
    uses org.jmp.bank.cloud.service.IBankService;
}