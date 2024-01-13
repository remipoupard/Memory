class Memory extends Program{

    final String[][] CATEGORIE=new String[][]{{"hello","bonjour"},
                                                {"bye","au revoir"},
                                                {"welcome","bienvenue"},
                                                {"today","aujourd'hui"},
                                                {"red","rouge"},
                                                {"green","vert"},
                                                {"blue","bleu"},
                                                {"tomorrow","demain"}};

    void testTypes(){
        Carte[] cc=newCarteParPaire(CATEGORIE[1]);
        assertEquals("carte 1=bye; carte 2=au revoir",toString(cc));
        Carte c1=selectCarte(0,cc);
        Carte c2=selectCarte(1,cc);
        assertEquals("bye",toString(c1));
        assertEquals("au revoir",toString(c2));
        Joueur j=newJoueur("azertyuiop");
        assertEquals(" azertyuiop  | points : 0",toString(j));
        Carte[] cc1=newCarteParPaire(CATEGORIE[2]);
        Carte[] cc2=newCarteParPaire(CATEGORIE[3]);
        Carte[] cc3=newCarteParPaire(CATEGORIE[4]);
        Carte[] pl=new Carte[] {selectCarte(0,cc),selectCarte(1,cc),selectCarte(0,cc1),selectCarte(1,cc1),selectCarte(0,cc2),selectCarte(1,cc2),selectCarte(0,cc3),selectCarte(1,cc3),};
        Plateau p=newPlateau(pl);
        assertEquals("bye; au revoir; welcome; bienvenue; today; aujourd'hui; red; rouge; ",toString(p));
    }

    void testEstNombre(){
        assertEquals(true,estNombre("1546"));
        assertEquals(true,estNombre("-1546"));
        assertEquals(false,estNombre("sf46"));
        assertEquals(false,estNombre("12-4"));
        assertEquals(false,estNombre("-----"));
        assertEquals(false,estNombre("46fsg"));
        assertEquals(false,estNombre("qdskh"));
    }

    void testToutDecouvert(){
        String[][] tab=new String[][]{{"toto","titi"},{"tutu","tyty"}};
        Carte[] plateau=stringToCarte(tab);
        Plateau p=newPlateau(plateau);
        assertEquals(false,toutDecouvert(p));
        for (int idx=0;idx<length(p.plateau)/2;idx++){
            p.plateau[idx].decouvert=true;
        }
        assertEquals(false,toutDecouvert(p));
        for (int idx=2;idx<length(p.plateau);idx++){
            p.plateau[idx].decouvert=true;
        }
        assertEquals(true,toutDecouvert(p));
    }

    Carte[] newCarteParPaire(String[] valeur){
        Carte c1=new Carte();//initialise c1
        Carte c2=new Carte();//initialise c2
        c1.valeur=valeur[0];//etablis la valeur de c1 au premier élément devaleur
        c2.valeur=valeur[1];//etablis la valeur de c2 au deuxième élément de valeur
        c1.paire=c2;//
        c2.paire=c1;
        c1.decouvert=false;
        c2.decouvert=false;
        c1.selection=false;
        c2.selection=false;
        Carte[] retour=new Carte[]{c1,c2};
        return retour;
    }

    String toString(Carte[] paire){
        return "carte 1="+paire[0].valeur+"; carte 2="+paire[1].valeur;
    }

    Carte selectCarte(int nbCarte, Carte[] paire){
        return paire[nbCarte];
    }

    String toString(Carte carte){
        return carte.valeur;
    }

    Joueur newJoueur(String pseudo){
        Joueur j=new Joueur();
        j.points=0;
        j.pseudo=pseudo;
        return j;
    }

    String toString(Joueur j){
        return " "+j.pseudo+"  | points : "+j.points;
    }

    Plateau newPlateau(Carte[] plateau){
        Plateau p=new Plateau();
        p.plateau=plateau;
        return p;
    }

    String toString(Plateau plateau){
        String retour = "";
        for (int indiceI =0 ; indiceI< length(plateau.plateau);indiceI++){
            retour+= toString(plateau.plateau[indiceI]) + "; ";
        }
        return retour;
    }


    Carte[] stringToCarte(String[][] categorie){
        Carte[] retour=new Carte[length(categorie,1)*2];
        for (int i=0;i<length(retour);i+=2){
            Carte[] cc=newCarteParPaire(categorie[i/2]);
            retour[i]=cc[0];
            retour[i+1]=cc[1];
        }
        return retour;
    }

    

    void affiche(int nombreDimension,Plateau p, int tour){
        final char NEW_LINE = '\n';
        final String display = "*** Welcome to your favourite game of Memory! *** " + NEW_LINE;   
        if(tour == 0){
            println(display); 
        }
        int compteur=0;                                                                
        for(int indiceI = 0; indiceI < nombreDimension; indiceI++){
            for (int indice = 0 ; indice < nombreDimension; indice++){
                print("----  ");
            }
            println();
            for (int indice = 0 ; indice < nombreDimension; indice++){
                compteur++;
                if(compteur>9){
                    if(p.plateau[compteur-1].decouvert==true){
                        print("|"+ANSI_RED+compteur+ANSI_WHITE+"|  ");
                    }
                    else{
                        print("|"+compteur+"|  ");
                    }
                }else{
                    if(p.plateau[compteur-1].decouvert==true){
                        print("|"+ANSI_RED+compteur+ANSI_WHITE+" |  ");
                    }
                    else{
                        print("|"+compteur+" |  ");
                    }
                }
            }
            println();
            for (int indice = 0 ; indice < nombreDimension; indice++){
                print("----  ");
            }
            println();
        }               
        
    }

    /*void consigne(){
        final char NEW_LIGNE = '\n';
        println(NEW_LIGNE+
            "  ======================================================================= " + NEW_LIGNE+
            "||                             " + ANSI_RED+"Memory"+ ANSI_WHITE+"                                    ||"+ NEW_LIGNE+
            "  ======================================================================="+NEW_LIGNE);
        println("ienvenue dans le jeu de Memory !"+NEW_LIGNE+

            "Le Memory est un jeu de société classique qui met à l'épreuve votre mémoire."+NEW_LIGNE+
            "Le but du jeu est de trouver toutes les paires de cartes correspondantes."+NEW_LIGNE+

            "Règles du jeu :"+NEW_LIGNE+
            "1. Le jeu se joue avec un ensemble de cartes face cachée."+NEW_LIGNE+
            "2. Retournez deux cartes à la fois pour trouver des paires."+NEW_LIGNE+
            "3. Si les cartes que vous retournez ne sont pas identiques, retournez-les face cachée."+NEW_LIGNE+
            "4. Rappelez-vous de l'emplacement des cartes que vous avez retournées pour pouvoir former des paires."+NEW_LIGNE+
            "5. Continuez à retourner les cartes jusqu'à ce que toutes les paires aient été trouvées."+NEW_LIGNE+
            "6. Le joueur qui trouve le plus grand nombre de paires gagne."+NEW_LIGNE+

            NEW_LIGNE+"Conseils :"+NEW_LIGNE+
            "- Concentrez-vous et mémorisez l'emplacement des cartes."+NEW_LIGNE+
            "- Jouez avec des amis pour rendre le jeu plus amusant et compétitif."+NEW_LIGNE+

            NEW_LIGNE+"Amusez-vous bien en jouant au Memory !"+NEW_LIGNE);
    }*/

    Joueur[] initialiseJoeur(){
        println("Combien de joueur êtes vous ? (max 4)");
        String taille = readString();
        while(!estNombre(taille)||stringToInt(taille)<=0||stringToInt(taille)>4){
            println("Combien de joueur êtes vous ? (max 4)");
            taille = readString();
        }
        int tailleInt=stringToInt(taille);
        Joueur [] listeJoueur = new Joueur[tailleInt];
        for (int indice = 0; indice < tailleInt ; indice++){
            println("Saisir le pseudo de joueur "+ (indice+1)+" SVP=  ");
            listeJoueur[indice] = newJoueur(readString());
        }
        return listeJoueur;
    }

    boolean estNombre(String str){
        int cpt=0;
        if(length(str)==0){
            return false;
        }
        if(charAt(str,cpt)=='-'){
            return estNombre(substring(str,1,length(str)));
        }
        while(cpt<length(str)&&(charAt(str,cpt)>='0'&&charAt(str,cpt)<='9')){
            cpt++;
        }
        return cpt==length(str);
    }

    int coup(Plateau p, int[] listeChoix){
        String choix  = readString();
        if (estNombre(choix)){
            if (stringToInt(choix)==-1){
                return -1;
            }
        }
        //regarder si chiffre
        while(!estNombre(choix)||stringToInt(choix)-1<0||stringToInt(choix)-1>length(p.plateau)-1||p.plateau[stringToInt(choix)-1].decouvert){
            println(ANSI_RED+"non valide, saisir à nouveau"+ANSI_WHITE);
            choix  = readString();
            if (estNombre(choix)){
                if (stringToInt(choix)==-1){
                    return -1;
                }
            }
        }
        return stringToInt(choix)-1;
    }

    boolean toutDecouvert(Plateau p){
        int idx=0;
        while(idx<length(p.plateau)&&p.plateau[idx].decouvert){
            idx++;
        }
        return idx==length(p.plateau);
    }

    boolean resolutionCoup(Joueur joueur, Plateau p, int[] listeChoix){
        if(equals(toString(p.plateau[listeChoix[0]].paire), toString(p.plateau[listeChoix[1]]))){
            joueur.points ++;
            p.plateau[listeChoix[0]].decouvert=true;
            p.plateau[listeChoix[1]].decouvert=true;
            return true;
        }
        return false;
    }

    String [][] fileAsTabString(String filename, int dim){
        // dim : taille du tableau
        dim = (dim*dim)/2;
        String [][] tab = new String[dim][2];
        extensions.File f = new extensions.File(filename);
        String content = "";

        for (int indiceI = 0; indiceI < dim;indiceI ++){
            for (int indiceJ = 0; indiceJ < 2 ; indiceJ ++){
                tab[indiceI][indiceJ] = (readLine(f) + " ");
            }
        }
        return tab;        
    }

    void finPartie(Joueur[] listeJoueur){
        int max=listeJoueur[0].points;
        int idxV=0;
        for(int i=0;i<length(listeJoueur);i++){
            if(listeJoueur[i].points>max){
                max=listeJoueur[i].points;
                idxV=i;
            }
        }
        println("le vainqueur est : "+listeJoueur[idxV].pseudo+"avec "+listeJoueur[idxV].points+"points !");
        //stringAsFile("vainqueur.txt",listeJoueur[idxV].pseudo+":"+listeJoueur[idxV].points);
    }

    int difficulty(){
        println("Choisir difficulté(1:4X4 ; 2:6X6)");
        String dif=readString();
        while(!estNombre(dif)||stringToInt(dif)>2||stringToInt(dif)<1){
            println("Choisir difficulté(1:4X4 ; 2:6X6)");
            dif=readString();
        }
        if(stringToInt(dif)==1){
            return 4;
        }
        return 6;
    }

    int randInt(){
        double r=random()*10;
        return (int)r;
    }

    void shuffle(Plateau p){
        Carte tmp;
        for(int i=0;i<length(p.plateau);i++){
            int j=randInt();
            while(j>length(p.plateau)){
                j=randInt();
            }
            tmp=p.plateau[i];
            p.plateau[i]=p.plateau[j];
            p.plateau[j]=tmp;
        }
    }

    String fileAsString(String filename) {
        extensions.File f = new extensions.File(filename);
        String content = "";
        while (ready(f)) {
            content = content + readLine(f) + '\n';

        }
        return content;
    }

    void consigne(){
        println("voulez vous un rappel des consigne ?(O/N)");
        String rep=readString();
        while(!equals(rep,"O")&&!equals(rep,"N")){
            println("voulez vous un rappel des consigne ?(O/N)");
            rep=readString();
        }
        if(equals(rep,"O")){
            println(ANSI_GREEN+fileAsString("ressources/text.txt")+ANSI_WHITE);
        }
    }

    void algorithm(){
        consigne();
        int nbDimension = difficulty();
        String[][] tab =fileAsTabString("ressources/données.txt",nbDimension);    
        Carte[] ta = stringToCarte(tab);
        Plateau p=newPlateau(ta);
        shuffle(p);
        affiche(nbDimension,p,0);
    
        Joueur[] listeJoueur=initialiseJoeur();
        int choix;
        int coup = 0;
        int[] listeChoix = new int[2];
        int indiceJoueur = 0;
        int taille = length(listeJoueur);
        Joueur joueur=listeJoueur[indiceJoueur];
        int idxCoup=0;
        boolean running = true;
        boolean rejoue;
        println("*Saisir "+ANSI_RED+"-1"+ANSI_WHITE+" pour finir et quitter le jeu*");
        while( !toutDecouvert(p) && running){

            affiche(nbDimension,p,coup);
            if(idxCoup==2){
                idxCoup=0;
                rejoue=resolutionCoup(joueur,p,listeChoix);
                //changement de joueur
                if(!rejoue){
                    println("Dommage :(");
                    if(indiceJoueur == length(listeJoueur)-1 ){
                        indiceJoueur= 0;
                    }else{
                        indiceJoueur++;
                    }
                }else{
                    println(ANSI_GREEN+"BRAVO !!! Tu as trouvé une paire,"+ANSI_WHITE);
                    println(ANSI_GREEN+"Tu gagne un point et tu peux rejouer :)"+ANSI_WHITE);
                }
            }

            joueur = listeJoueur[indiceJoueur];

            println( ANSI_YELLOW+" saisir votre choix "+ ANSI_WHITE+toString(joueur));
            choix = coup(p,listeChoix);
            if (choix>-1){
                println("carte: "+toString(p.plateau[choix]));
                listeChoix[idxCoup]=choix;
                coup +=1;
            }else{
                running = false;
            }
            idxCoup++;
        }
        finPartie(listeJoueur);
        println("Au revoir!");
    }
}