package Bipas.account;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.thealtening.AltService;
import com.thealtening.api.TheAltening;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.net.Proxy;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 21:44
 */
public class Account {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final AltService ALT_SERVICE = new AltService();

    public void loginCracked(String name) {
        try {
            this.ALT_SERVICE.switchService(AltService.EnumAltService.MOJANG);
            Minecraft.getMinecraft().session = new Session(name, "", "", "LEGACY");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void login(String email, String password) {
        try {
            this.ALT_SERVICE.switchService(AltService.EnumAltService.MOJANG);
            YggdrasilUserAuthentication service = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
            service.setUsername(email);
            service.setPassword(password);
            service.logIn();
            Minecraft.getMinecraft().session = new Session(service.getSelectedProfile().getName(), service.getSelectedProfile().getId().toString(), service.getAuthenticatedToken(), "LEGACY");
        } catch (Exception e) {
        }
    }


    public void generateAltening(String apiKey) {
        try {
            this.ALT_SERVICE.switchService(AltService.EnumAltService.THEALTENING);
            TheAltening theAltening = new TheAltening(apiKey);
            TheAltening.Asynchronous asynchronous = new TheAltening.Asynchronous(theAltening);
            asynchronous.getAccountData().thenAccept(accountData -> {
                YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
                yggdrasilUserAuthentication.setUsername(accountData.getToken());
                yggdrasilUserAuthentication.setPassword(accountData.getUsername());
                try {
                    yggdrasilUserAuthentication.logIn();
                    mc.session = new Session(yggdrasilUserAuthentication.getSelectedProfile().getName(), yggdrasilUserAuthentication.getSelectedProfile().getId().toString(), yggdrasilUserAuthentication.getAuthenticatedToken(), "mojang");
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
