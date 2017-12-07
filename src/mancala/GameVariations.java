package mancala;

/** 
 * The various board games which we can play. This enumerated type supports a variety of queries about
 * the specifications of the various games, such as the board type (e.g. number of rows), the number of
 * bins per row, the initial number of seeds in each bin, and where the game comes from.
 * 
 * To add a new game, insert its name (in all caps) in the list at the top, according to the
 * shape of the game's board. (Be careful about the two different kinds of two-row games; one
 * *with* bins to store your winnings, and one without.) Then add it to the switch statements 
 * for getColumns(), initialSeedsPerBin(), and getOriginCountry( ).
 * 
 * @author Darrah Chavey
 */
public enum GameVariations { 
	
	
	// Within each board type, these games are listed in the order in which they appear in Lawrence Russ'  
	// book "Mancala Games". We do not currently include games that are not in that book.
	
	/* One row game: */
	TCHUKA_RUMA, TCHUKA_RUMA_GENERALIZED,
	
	/* Two row games, no end bins necessary: */
	WARI, OWARE_1, ERHERHE, SONGO, ADJI_BOTO, MBANGBI, UM_EL_BANGARA, UM_EL_TUWEISAT, BECHI,
	QELAT_1, VAI_LUNG_THLAN, ESON_XORGUL, TORGUZ_XORGOL, ENDODOI_6, ENDODOI_8, ENDODOI_10, AYOAYO, YOVODJI, TAMTAM_APACHI,
	WARRI, TEGRE, NDOTO, BAO_1, DABUDA, GAMACHA, YADA, SADEQA_1, SADEQA_2, TAPATA, J_ODU, MBOTHE, LAMLAMETA,
	LAMI, GIUTHI, ENKESHUI, BAO_2, LEYLA_GOBALE, BOSH, GABATA_1, UM_EL_BANAT, QELAT_2, WEG, SADEQA_3,
	FANGAYA, GEZUWA, SADIQA, GABATA_2, SADEQA_4, ANDOT, ADI, ANANA, OWARE_2, MEWELAD, LAMOSH_1,
	LAMOSH_2, YIT_NURI, ANYWOLI, GABATA_3, J_ERIN_1, J_ERIN_2, WOURI, BA_AWA, PANDI, LONGBEA_A_CHA, 
	OLINDA, OT_TJIN, MOTIQ, MEUSUEB,
	
	/* Two row games, must have end bins: */
	PALLAM_KUZHI, PALLANGULI, DAKON, GALATJANG, SUNGKA, 
	
	// Two row ovals, not currently able to set up the game board for these games, hence commented out:
	// DEKA, IMBELECE, NAMBAYI, MBELELE	(All from Zaire)
	
	/* Three row games: */
	GABATA_4, ABALALA_E, 
	
	/* Four row games: */
	NJOMBWA, LELA, DWONG, BARUMA, MARUBA, MOFUBA, NCHOMBWA, NCHUWA, TSCHUBA, MSUWA, SPRETA,
	TSORO, OTU, OTRA, MULABALABA_1, MULABALABA_2, NAKABILE, NCHUBA, NSUMBI, LUSOLO, BARE, 
	RYAKATI, KACHIG, PEREAUNI, MANGOLA_1, LEKA, MANGOLA_2, K_HUS, MWAMBULULA, OTEP, MWESO,
	MUTITEBA, KUBUGUZA, IGISORO, CHORO_1, CHORO_2, KISOLO, KATRA, BAO_KISWAHILI, 
	BAO_LA_KUJIFUNZA, BAO_LA_KISWAHILI 	;
	
	
	
	/** From the enumerated value, return a properly capitalized string for the name of
	 *  that game.
	 *  BUG: This does not properly handle punctuation marks in the game name.
	 *  
	 *  @return A more readable form of the name of the game.
	 */
	public String getName( ) {
		String[] words = name().split("_");
		StringBuffer formattedName = new StringBuffer();
		for (int index = 0; index < words.length; index++ ) {
			formattedName.append( words[index].subSequence(0, 1).toString().toUpperCase() );
			formattedName.append( words[index].substring(1).toLowerCase() );
			if (index < words.length - 1) {
				formattedName.append( ' ' );
			}
		}
		return formattedName.toString();
	}
	
	/**
	 * Given a string representation of a game, return the enumerated value that
	 * corresponds to that game. It makes a valiant attempt to do this, but can
	 * fail, in which case it returns null. If the string does not correspond to
	 * a legal enumeration value, it first attempts to replace blank spaces with
	 * underscores, then if necessary tries adding "_1" to the name to disambiguate
	 * between multiple games with the same name (which may result in a different
	 * game than you were expecting). <br>
	 * BUG: At present it does not attempt to strip embedded punctuation marks, 
	 * nor to handle variations in diacritical marks within the spelling.
	 * 
	 * @param gameName The string version of the name of the game
	 * @return The enumeration type version of the name of the game.
	 */
	public static GameVariations getGameVariation( String gameName ) {
		GameVariations gameVariation;
		
		gameName = gameName.toUpperCase();
		try {
			gameVariation = valueOf(gameName);
		} catch (IllegalArgumentException e1) {
			gameName = gameName.replace(" ", "_");
			try {
				gameVariation = valueOf(gameName);
			} catch (IllegalArgumentException e2) {
				gameName = gameName + "_1";
				gameVariation = valueOf(gameName);		// If this fails, there's a program error
			}
		}
		return gameVariation;
	}
	
	/** 
	 * Knows the board type of each of the game variations.
	 * 
	 *  @return The board type of this particular game.
	 */
	public BoardTypes getBoardType( ) {
		// This requires that the enumerated type lists the game in the appropriate order:
		// One row games, then two row games, etc. The last game of each type is used as a
		// flag, so new games of that type should be added before that one, or else this
		// code will need to be updated.
		if ((this == TCHUKA_RUMA) || (this == TCHUKA_RUMA_GENERALIZED))  {
			return BoardTypes.ONE_ROW;
		} else if (this.ordinal() <= MEUSUEB.ordinal()) {
			return BoardTypes.TWO_ROW;			
		} else if (this.ordinal() <= SUNGKA.ordinal()) {
			return BoardTypes.TWO_ROW_WITH_ENDS;	
		} else if (this.ordinal() <= ABALALA_E.ordinal()) {
			return BoardTypes.THREE_ROW;	
		} else {
			return BoardTypes.FOUR_ROW;	
		}
	}
	
	/** 
	 * This method knows the number of columns for each game, i.e. the number of bins in each row.
	 * This does <i>not</i> include the end bins into which we often collect seeds we've won.
	 * BUG: This does <i>not</i> properly handle the situation where a game can be played with
	 * a variety of different size boards.
	 * 
	 * @return The number of columns used by this particular game. 
	 */
	public int getColumns() {
		switch (this) {
			case UM_EL_TUWEISAT: return 3;
			
			case BECHI: return 4;
			
			case TCHUKA_RUMA: case ADJI_BOTO: case MBANGBI: case UM_EL_BANGARA: case ESON_XORGUL: case BOSH: case LONGBEA_A_CHA: return 5;
			
			case WARI: case OWARE_1: case OWARE_2: case ERHERHE: case QELAT_1: 
			case VAI_LUNG_THLAN: case AYOAYO: case YOVODJI: case TAMTAM_APACHI: case WARRI: case TEGRE:
			case GABATA_1: case GABATA_2: case GABATA_3: case GABATA_4: case UM_EL_BANAT: case QELAT_2: case WEG:
			case FANGAYA: case GEZUWA: case SADEQA_4: case ANDOT: case ADI: case ANANA: case MEWELAD: case LAMOSH_1:
			case LAMOSH_2: case J_ERIN_1: case J_ERIN_2: case WOURI: case BA_AWA: case MEUSUEB: case ABALALA_E: case ENDODOI_6: return 6;
			
			case SONGO: case BAO_2: case YIT_NURI: case PALLAM_KUZHI: case PALLANGULI: case PANDI: case OLINDA:
			case MOTIQ: case LELA: case KISOLO: return 7; 
			
			case NDOTO: case BAO_1: case NJOMBWA: case DWONG: case BARUMA:
			case MARUBA: case MOFUBA: case OTU: case OTRA: case MULABALABA_1: case NAKABILE: case NSUMBI: case LUSOLO:
			case RYAKATI: case PEREAUNI: case LEKA: case MWAMBULULA: case OTEP: case MWESO: case MUTITEBA: case KUBUGUZA:
			case IGISORO: case CHORO_1: case CHORO_2: case KATRA: case ENDODOI_8: return 8; 
			
			case TORGUZ_XORGOL: return 9;
			
			case SADEQA_1: case SADEQA_2: case SADEQA_3: case MBOTHE: case LAMI: case SADIQA: case OT_TJIN: case ENDODOI_10: return 10;
			
			case GAMACHA: case YADA: case TAPATA: case LAMLAMETA: case ANYWOLI: case BARE: return 12;
			
			case NCHUBA: return 16;
			
			case TCHUKA_RUMA_GENERALIZED: return 0;
			
			default: System.out.println("Code failure in GameVariation.getColumns()");
					return 0;
		}
	}
	
	/** 
	 * Knows the number of seeds to start in the bins initially for uniform distribution games.
	 * BUG: This does <i>not</i> properly handle the situation where a game can be played with
	 * a variety of choices for this number, nor where the game can start with a non-uniform
	 * distribution of seeds.
	 * 
	 * @return The initial number of seeds per bin, depending upon the game selected. 
	 */
	public int initialSeedsPerBin() {
		switch (this) {
			case NDOTO: case BAO_1: case MBOTHE: case LAMLAMETA: case LAMI: case NCHUWA: case TSCHUBA: case MULABALABA_1: 
			case MULABALABA_2: case NSUMBI: case LUSOLO: case BARE: case RYAKATI: case KACHIG: case PEREAUNI: case LEKA: 
			case OTEP: case CHORO_1: case CHORO_2: case KATRA: return 2;
			
			case UM_EL_TUWEISAT: case GABATA_1: case GABATA_3: case FANGAYA: case GEZUWA: case SADIQA: 
			case ABALALA_E: return 3;
			
			case WARI: case OWARE_1: case OWARE_2: case ERHERHE: case QELAT_1: case ENDODOI_6: case ENDODOI_8: case ENDODOI_10: case AYOAYO: 
			case YOVODJI: case TAMTAM_APACHI: case WARRI: case TEGRE: case DABUDA: case BAO_2: case LEYLA_GOBALE: 
			case BOSH: case UM_EL_BANAT: case QELAT_2: case WEG: case SADEQA_3: case SADEQA_4: case GABATA_2: 
			case ANDOT: case ADI: case ANANA: case MEWELAD: case LAMOSH_1: case LAMOSH_2: case YIT_NURI: case ANYWOLI: 
			case J_ERIN_1: case J_ERIN_2: case WOURI: case BA_AWA: case PALLAM_KUZHI: case OLINDA: case MEUSUEB: return 4;
			
			case SONGO: case UM_EL_BANGARA: case VAI_LUNG_THLAN: case PANDI: case LONGBEA_A_CHA: return 5;
			
			case BECHI: case PALLANGULI: return 6;
			
			case MBANGBI: return 8;
			
			case ESON_XORGUL: case TORGUZ_XORGOL: return 9;
			
			case ADJI_BOTO: return 10;
			
			default: return 0;
		}
	}
	
	
	/** 
	 * Knows the country of origin for the games included in the program.
	 * 
	 * @return A string version of the country of origin recorded in sources for the game.
	 */
	public String getOriginCountry( ) {
		switch (this) {
		
		case WARRI: return "Barbados";
		case YOVODJI: return "Benin";
		case SONGO: case MBANGBI: return "Cameroon";
		case DABUDA: return "Djibouti";
		
		case BECHI: case QELAT_1: case TEGRE: case GAMACHA: case YADA: case SADEQA_1: case SADEQA_2: 
			case TAPATA: case LAMLAMETA: case LAMI: case GABATA_1: case QELAT_2: case WEG: case SADEQA_3: 
			case FANGAYA: case GEZUWA: case SADIQA: case GABATA_2: case SADEQA_4: case MEWELAD: 
			case LAMOSH_1: case LAMOSH_2: case YIT_NURI: case ANYWOLI: case GABATA_3: case GABATA_4: 
			case ABALALA_E: case BARE: return "Ethiopia";

		case OWARE_1: case ERHERHE: case TAMTAM_APACHI: case ADI: case ANANA: case OWARE_2: case BA_AWA: return "Ghana";
		case VAI_LUNG_THLAN: case PANDI: case LONGBEA_A_CHA: case PALLAM_KUZHI: case PALLANGULI: return "India";
		case OT_TJIN: case MOTIQ: case MEUSUEB: case DAKON: case GALATJANG: return "Indonesia";
		case ENDODOI_6: case ENDODOI_8: case ENDODOI_10: case NDOTO: case MBOTHE: case GIUTHI: case BAO_1: case ENKESHUI: case KACHIG: case PEREAUNI: return "Kenya";
		case KATRA: return "Madagascar";
		case NCHOMBWA: case NCHUWA: case MSUWA: case SPRETA: return "Malawi";
		case TCHUKA_RUMA: return "Malaysia";
		case WARI: case WOURI: return "Mali";
		case NJOMBWA: return "Mozambique"; 
		case K_HUS: return "Namibia";
		case AYOAYO: case J_ODU: case J_ERIN_1: case J_ERIN_2: return "Nigeria";
		case SUNGKA: return "Phillipines"; 
		case ESON_XORGUL: case TORGUZ_XORGOL: return "Russia";
		case KUBUGUZA: case IGISORO: case CHORO_1: case CHORO_2: return "Rwanda";
		case LEYLA_GOBALE: case BOSH: return "Somalia"; 
		case TSCHUBA: return "South Africa"; 
		case OLINDA: return "Sri Lanka";
		case UM_EL_BANGARA: case UM_EL_TUWEISAT: case UM_EL_BANAT: case ANDOT: case RYAKATI: case OTEP: return "Sudan"; 
		case ADJI_BOTO: return "Surinam";
		case BAO_2: case BAO_KISWAHILI: case BAO_LA_KUJIFUNZA: case BAO_LA_KISWAHILI: return "Tanzania";
		case MARUBA: case MOFUBA: return "Transvaal";
		case MWESO: return "Uganda"; 
		
		case LELA: case DWONG: case BARUMA: case OTU: case OTRA: case NSUMBI: case LUSOLO: case MANGOLA_1: 
			case LEKA: case MANGOLA_2: case MUTITEBA: case KISOLO: return "Zaire";

		case MULABALABA_1: case MULABALABA_2: case NAKABILE: case NCHUBA: case MWAMBULULA: return "Zambia";
		case TSORO: return "Zimbabwe";

		default: return "Null"; // Should never be reached.
		}
	}

}