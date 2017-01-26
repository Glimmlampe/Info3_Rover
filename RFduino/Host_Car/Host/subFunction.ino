/***************************************************************
/** Rechnet lesbare Werte in Steuerungsbefehle um
/** rate in %, angulat in °, period in ms 
****************************************************************/

void distributor(int rate, int angular, int period)
{
	Serial.print("subFunction rate:  ");
	Serial.println(rate);
	Serial.print("subFunction angular:  ");
	Serial.println(angular);
	Serial.print("subFunction period:  ");
	Serial.println(period);
	
	//********************************************************************************
	//Pause
	//********************************************************************************
	if (rate == 0 && angular == 0)
		allStop();
	//********************************************************************************
	//Geschwindigkeit
	//********************************************************************************
	else if (rate != 0 && period > 0)
		move(rate, period);
	//*********************************************************************************
	//Winkel
	//*********************************************************************************
	else if(angular != 0)
		turn(angular);
	//********************************************************************************
	//Fehler
	//********************************************************************************
	else
		Serial.println("Some mixed up values");
	
	busy = false;
}

void move(int rate, int period)
{
	int incoMov;
	if(rate > 0)
	{
		Serial.print("Vorwaerts: ");
    Serial.println(rate);
    
		incoMov = rate;  
		incoMov = constrain(map(incoMov, 1, 100, 100, 250),0, 250);	// % -> PWM, max 250, not 255 for safety reasons

    Serial.print(" ");
    Serial.println(incoMov);
    
		backward(incoMov, period);					// Funktion fuer Fahrbefehl
	}else if(rate < 0 )
	{
		Serial.print("Rueckwaerts");
    Serial.println(rate);
    
		incoMov = rate * (-1);
		incoMov = constrain(map(incoMov, 1, 100, 100, 250),0, 250);	// % -> PWM, max 250, not 255 for safety reasons

    Serial.print(" ");
    Serial.println(incoMov);
    
		forward(incoMov, period);						// Funktion fuer Fahrbefehl
	}
	Serial.print("Rate: ");
	Serial.println(incoMov);
	Serial.println("Abgeschlossen");
}

void turn(int angle)
{
	if (angle < 0)							/*CURVE RIGHT*/
	{
		Serial.println("drehe Rechts");
		angle = angle * (-1);

		Serial.print("Angle: ");
		Serial.println(angle);

		curveRight(angle);
		Serial.println("Abgeschlossen");
	}else if (angle > 0)					/*CURVE LEFT*/
	{
		Serial.println("drehe Links");
		angle = angle;

		Serial.print("Angle: ");
		Serial.println(angle);

		curveLeft(angle);
		Serial.println("Abgeschlossen");
	}
}

/*Funktion fuer die Vorwaersfahrt*/
void forward(int rate, int period)
{
  Serial.print("XXX rate:  ");
  Serial.println(rate);
  
//	anlauf(rate, 'f');								// sanft Anlauf
	analogWrite(AnRe1, rate);						//
	digitalWrite(AnRe2, LOW);						// Ansteuern der H-Bruecke
	analogWrite(AnLi1, rate);						//
	digitalWrite(AnLi2, LOW);						//

	delay(period);
//	runterfahren(rate, 'f');						// sanftes Bremsen
	allStop();
}

/*Funktion fuer die Rueckwaertsfahrt*/
void backward(int rate, int period)
{
  Serial.print("XXX rate:  ");
  Serial.println(rate);
  
//	anlauf(rate, 'b');								// sanft Anlauf
	digitalWrite(AnRe1, LOW);						//
	analogWrite(AnRe2, rate);						// Ansteuern der H-Bruecke
	digitalWrite(AnLi1, LOW);						//
	analogWrite(AnLi2, rate);						//

	delay(period);
//	runterfahren(rate, 'b');						// sanftes Bremsen
	allStop();
}

/*Drehe nach Rechts*/
void curveRight(int angular)
{
	Serial.print("ich drehe um ");
	Serial.println(angular);
	
	analogWrite(AnRe1, 180);
	digitalWrite(AnRe2, LOW);
	analogWrite(AnLi2, 180);
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
	
	analogWrite(AnRe2, 160);
	digitalWrite(AnRe1, LOW);
	analogWrite(AnLi1, 160);
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

