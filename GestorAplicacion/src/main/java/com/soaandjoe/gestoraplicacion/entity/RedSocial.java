package com.soaandjoe.gestoraplicacion.entity;

/**
 *
 * @author andres
 */
public enum RedSocial {

    TWITTER(1),
    FACEBOOK(2),
    GOOGLE(3);

    private final int idRedSocial;

    private RedSocial(int idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public int getIdRedSocial() {
        return idRedSocial;
    }
    
    public static synchronized RedSocial getRedSocialById(int idRedSocial){
        for (RedSocial redSocial : RedSocial.values()) {
            if(redSocial.getIdRedSocial() == idRedSocial){
                return redSocial;
            }
        }
        return null;
    }
}
