package br.com.hitbox.infra.config.filter;

import java.util.UUID;

public class TenantContext {
    private static final ThreadLocal<UUID> COMPANY =
            new ThreadLocal<>();

    public static void setCompanyId(
            UUID companyId) {

        COMPANY.set(companyId);
    }

    public static UUID getCompanyId() {

        return COMPANY.get();
    }

    public static void clear() {

        COMPANY.remove();
    }
}
