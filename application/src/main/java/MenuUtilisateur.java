import java.util.Scanner;

public abstract class MenuUtilisateur {

    public void displayPageModifierSonProfil(Utilisateur user, String str){

        Scanner keyb = new Scanner(System.in);

        if (str.equals("username")) {
            System.out.print("MODIFIER VOTRE PROFIL\n" +
                    "\nEntrez votre nouveau nom d'utilisateur: ");
            String username = keyb.nextLine();
            user.setUsername(username);
        }

        if (str.equals("password")){
            System.out.print("Entrez votre nouveau mot de passe: ");
            String password = keyb.nextLine();
            user.setPassword(password);
        }
    }

}
