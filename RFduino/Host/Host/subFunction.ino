/* rate in %, angulat in °, period in ms */
void distributor(int rate, int angular, int period)
{
	Serial.print("subFunction rate:  ");
	Serial.println(rate);
	Serial.print("subFunction angular:  ");
	Serial.println(angular);
	Serial.print("subFunction period:  ");
	Serial.println(period);
	
	
	if (rate == 0 && angular == 0) 				/*HALT*/
	{
		allStop();
	}
	//********************************************************************************
	//Geschwindigkeit
	//********************************************************************************
	else if (rate < 0 && period != 0)			/*BACKWARDS*/
	{                  
		Serial.println("Rueckwaerts");
		int incoBack = rate * (-1);  
		if(incoBack)
			incoBack = constrain(map(incoBack, 1, 100, 60, 250),0, 250);	// % -> PWM, max 250, not 255 for safety reasons
    /*	map(VARIABLE, lowEntryVal, highEntryVal, lowDestVal, highDestVal) 
	*	constrain(VARIABLE, lowestVal, HighestVal)
	*/
    
		Serial.print("Rate: ");
		Serial.println(incoBack);

		//period = period * 900;						// Dauer in ms
		backward(incoBack, period);					// Funktion fuer Fahrbefehl
		Serial.println("Abgeschlossen");
	}else if (rate > 0 && period != 0) 			/*FOREWARD*/
	{
		Serial.println("Vorwaerts");
		
		int incoFore=rate;
		if(incoFore){
			incoFore = constrain(map(incoFore, 1, 100, 60, 250),0, 250);
		}
    
		Serial.print("Rate: ");
		Serial.println(incoFore);

		//period = period * 900;						// Dauer in ms
		forward(rate, period);						// Funktion fuer Fahrbefehl
		Serial.println("Abgeschlossen");
	}
	//*********************************************************************************
	//Winkel
	//*********************************************************************************
	else if (angular < 0)					/*CURVE RIGHT*/
	{
		Serial.println("drehe Rechts");
		angular = angular * (-1);

		Serial.print("Angular: ");
		Serial.println(angular);

		curveRight(angular);
		Serial.println("Abgeschlossen");
	}else if (angular > 0)					/*CURVE LEFT*/
	{
		Serial.println("drehe Links");
		angular = angular;

		Serial.print("Angular: ");
		Serial.println(angular);

		curveLeft(angular);
		Serial.println("Abgeschlossen");
	}
	//int k = Serial.available();
	//Serial.print("k= ");
	//Serial.println(k);
	busy = false;
}

/*Funktion fuer die Vorwaersfahrt*/
void forward(int rate, int period)
{
	anlauf(rate, 'f');								// sanft Anlauf
	analogWrite(AnRe1, rate);						//
	digitalWrite(AnRe2, LOW);						// Ansteuern der H-Bruecke
	analogWrite(AnLi1, rate);						//
	digitalWrite(AnLi2, LOW);						//

	delay(period);
	runterfahren(rate, 'f');						// sanftes Bremsen
	allStop();
}

/*Funktion fuer die Rueckwaertsfahrt*/
void backward(int rate, int period)
{
	anlauf(rate, 'b');								// sanft Anlauf
	digitalWrite(AnRe1, LOW);						//
	analogWrite(AnRe2, rate);						// Ansteuern der H-Bruecke
	digitalWrite(AnLi1, LOW);						//
	analogWrite(AnLi2, rate);						//

	delay(period);
	runterfahren(rate, 'b');						// sanftes Bremsen
	allStop();
}

/*Drehe nach Rechts*/
void curveRight(int angular)
{
	Serial.print("ich drehe um ");
	Serial.println(angular);
	
	analogWrite(AnRe1, 125);
	digitalWrite(AnRe2, LOW);
	analogWrite(AnLi2, 125);
	digitalWrite(AnLi1, LOW);
	
	int directionTime = angular * angularFactor;	// Faktor ist als globale Konstante gespeichert
	delay(directionTime);
	allStop();
}

/*Drehe nach Links*/
void curveLeft(int angular)
{
	Serial.print("ich drehe um ");
	Serial.println(angular);
	
	analogWrite(AnRe2, 125);
	digitalWrite(AnRe1, LOW);
	analogWrite(AnLi1, 125);
	digitalWrite(AnLi2, LOW);
	
	int directionTime = angular * angularFactor;	// Faktor ist als globale Konstante gespeichert
	delay(directionTime);
	allStop();
}

/*Stop-Funktion*/
void allStop()
{
	digitalWrite(AnRe1, LOW);						// Legt alle Eingaenge der H-Bruecke auf Masse
	digitalWrite(AnRe2, LOW);						// und stoppt somit die Antriebe
	digitalWrite(AnLi1, LOW);						// wuerde auch funktionieren wenn alle Eingaenge auf High liegen wuerden
	digitalWrite(AnLi2, LOW);
}

/*Funktion des Sanftanlaufes*/
void anlauf(int rate, char direction)
{
	switch (direction)								// es wird zwischen dem Sanftanlauf fuer die Vor- und Rueckwaertsfahrt unterschieden
	{
	case 'f':										// fuer Vorwaerts
		for (int i = 0; i < rate; i++)				// hochfahren des PWM auf die gewuenschte Geschwindigkeit
		{
			analogWrite(AnRe1, i);
			digitalWrite(AnRe2, LOW);
			analogWrite(AnLi1, i);
			digitalWrite(AnLi2, LOW);

			delay(1.5);
		}
		break;
	case 'b':										// fuer Rueckwaerts
		for (int i = 0; i < rate; i++)				// hochfahren des PWM auf die gewuenschte Geschwindigkeit
		{
			digitalWrite(AnRe1, LOW);
			analogWrite(AnRe2, i);
			digitalWrite(AnLi1, LOW);
			analogWrite(AnLi2, i);

			delay(1.5);
		}
		break;
	default:										// bei Unklaren Verhaeltnissen ist der Antrieb gesprerrt
		digitalWrite(AnRe1, LOW);
		digitalWrite(AnRe2, LOW);
		digitalWrite(AnLi1, LOW);
		digitalWrite(AnLi2, LOW);
	break;


	}
}

/*	Funktion zum sanften Abbremsen
	Funktionsweise wie beim Sanftanlauf	*/
void runterfahren(int rate, char direction)
{
	switch (direction)
	{
	case 'f':
		for (int i = rate; i > 0; i--)
		{
			analogWrite(AnRe1, i);
			digitalWrite(AnRe2, LOW);
			analogWrite(AnLi1, i);
			digitalWrite(AnLi2, LOW);

			delay(2);
		}
		break;
	case 'b':
		for (int i = rate; i > 0; i--)
		{
			digitalWrite(AnRe1, LOW);
			analogWrite(AnRe2, i);
			digitalWrite(AnLi1, LOW);
			analogWrite(AnLi2, i);

			delay(2);
		}
		break;
	default:
		digitalWrite(AnRe1, LOW);
		digitalWrite(AnRe2, LOW);
		digitalWrite(AnLi1, LOW);
		digitalWrite(AnLi2, LOW);
		break;
	}
}

