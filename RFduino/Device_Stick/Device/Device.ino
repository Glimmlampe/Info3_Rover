/* Eingangsstring: 'Geschwindigkeit in %';'Winkel in °';'Zeit in ms'*/

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
	int nrOfBytes = Serial.available();					//Erfassung des belegten Buffers		
	if (nrOfBytes < 5)					//Sollte der Buffer nur zum Teil befuellt worden sein wird dieser hier geloescht
	{
		//falls zu wenig Daten, check ob nach 100 ms immer noch der fall, dann leeren
		int tmp = nrOfBytes;
		delay(100);
		nrOfBytes = Serial.available();
		if(tmp >= nrOfBytes)
			emptyBuffer(nrOfBytes);
	}
	else												// wenn String vollstaendig vorhanden ist wird die Bearbeitung gestartet.
	{
		Serial.print("Anzahl Bytes: ");
		Serial.println(nrOfBytes);
		for (int i = 0; i < nrOfBytes; i++)
		{
		  Data[i] = Serial.read();							//Auslesen des Buffers und schreiben
		}

		Serial.print("I recieved: ");						//gibt den empfangen String aus
		Serial.println(Data);

		packet.rate = 0;
		packet.angular = 0;
		packet.period = 0;

														//Auflösen des Strings in
		packet.rate = atoi(strtok(Data, ";"));				//Geschwindigkeit
		packet.angular = atoi(strtok(NULL, ";"));			//Winkel
		packet.period = atoi(strtok(NULL, ";"));			//Dauer

		RFduinoGZLL.sendToHost((char *)&packet, sizeof(packet));

		Serial.println("sent to Host");
		Serial.println(packet.rate);
		Serial.println(packet.angular);
		Serial.println(packet.period);

		memset(Data, 0, sizeof(Data));                         //leeren des Einlese-Strings
	}
}

void emptyBuffer(int nrBytes)
{
	for (int i = 0; i < nrBytes; i++) {
		wastebin[i] = Serial.read();
	}
}



