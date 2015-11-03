#include<stdio.h>
#include <string.h>
#include <RFduinoGZLL.h>

device_t role = DEVICE0;

char wastebin[10];											//Zu vernachlässigendes Array
char Data[20];

// custom packet max size 20 bytes.
struct myCustomPacket_t
{
	int rate = 0;
	int angular = 0;
	int period = 0;  
};
struct myCustomPacket_t packet;

void setup()
{
	RFduinoGZLL.begin(role);
	Serial.begin(9600);  
}

void loop()
{

	int h = Serial.available();							//Erfassung des belegten Buffers

	if (1 < h < 5)										//Sollte der Buffer nur zum Teil befüllt worden sein wir dieser hier gelöscht
	{
		int h1 = h;										//löschen tritt ein wenn sich die länge des Buffers nicht innerhalb
		delay(100);										//von 100ms
		if (Serial.available() < 5)
		{
			if (Serial.available() == h1)
			{
				for (int i = 0; i < h; i++)
				{
					wastebin[i] = Serial.read();		//leeren des Buffers, durch auslesen und schreiben in den "Mülleimner
				}
			}
		}
		if (Serial.available() > 10)					//buffer wird gelöscht wenn mehr als nur ein String vorhanden ist.
		{
			for (int i = 0; i < h; i++)
			{
				wastebin[i] = Serial.read();
			}
		}
	}

	if (Serial.available() > 4)							// wenn String vollständig vorhanden ist wird die Bearbeitung gestartet.
	{
		int h = Serial.available();
		Serial.print("h= ");
		Serial.println(h);
		for (int i = 0; i < h; i++)
		{
			Data[i] = Serial.read();					//Auslesen des Buffers und schreiben
		}

		Serial.print("I recieved: ");					//gibt den empfangen String aus
		Serial.println(Data);

		packet.rate=0;
		packet.angular=0;
		packet.period=0;

		packet.rate = atoi(strtok(Data, ";"));			//auflösen des Strings in die Geschwindigkeit
		packet.angular = atoi(strtok(0, ";"));			//winkel
		packet.period = atoi(strtok(0, ";"));			//Dauer

		RFduinoGZLL.sendToHost((char *)&packet, sizeof(packet));

		Serial.println("sent to Host");
		Serial.println(packet.rate);
		Serial.println(packet.angular);
		Serial.println(packet.period);

		//memset(Data, 0, sizeof(Data));                         //leeren des Einlese-Strings
	}
}





