#!/bin/bash

if ! command -v jq /dev/null;
then
	echo "Polecenie 'jq' jest wymagane do działania programu."
	echo "Wpisz to polecenie w terminalu, aby dowiedzieć się więcej."
	exit
fi

if ! command -v curl /dev/null;
then
	echo "Polecenie 'curl' jest wymagane do działania programu."
	echo "Wpisz to polecenie w terminalu, aby dowiedzieć się więcej."
	exit
fi


echo "-----REZERWACJA-1---------------------------------------------------------------------------"
printf "\n"
curl "http:/localhost:8080/app"

printf "\n\n[Server]: \n"
echo "Wybierz datę w jakiej chciałbyś/chciałabyś zobaczyć film (między 10:00 a 20:00): "

data="2022-10-18-12-16"
IFS='-'
read -a datearr <<< "$data"

year=${datearr[0]}
month=${datearr[1]}
day=${datearr[2]}
from=${datearr[3]}
to=${datearr[4]}


printf "\n[Klient]: \n"
echo "data: $year-$month-$day, od godziny: $from do godziny: $to"


printf "\n[Serwer]: \n"
echo "Filmy ($year-$month-$day, od godziny: $from do godziny: $to)"
curl -s "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings" | jq -r '.'


echo
echo "Wybierz id filmu, który chcesz obejrzeć: "

printf "\n[Klient]: \n"

idScreening=3
echo "$idScreening"

printf "\n[Serwer]: \n"
curl -s "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats" | jq '.'

echo 
echo "Podaj imie, nazwisko i bilety jakie chcesz kupić (dorosły - 25zł, student - 18zł, dziecko - 12,50zł):"

printf "\n[Klient]: \n"
name="Jan"
surname="Kowalski"
dateOfBooking="2022-10-18 10:00"
tickets="2-dorosły 3-dziecko 4-student"
echo "$name $surname $tickets"

response=$(curl -s -X POST "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations" -H "Content-Type: application/json" -d ' { "name": "'"$name"'", "surname": "'"$surname"'", "dateOfBooking": "'"$dateOfBooking"'", "tickets": "'"$tickets"'" } ')

id=$(jq -r '.id' <<< $response)

printf "\n[Serwer]: \n"
echo "Twoja rezerwacja: "
curl -s "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations/$id" | jq '.'
echo


echo "-----REZERWACJA-2---------------------------------------------------------------------------"
printf "\n"
curl "http:/localhost:8080/app"

printf "\n\n[Server]: \n"
echo "Wybierz datę w jakiej chciałbyś/chciałabyś zobaczyć film (między 10:00 a 20:00):"

data="2022-10-18-12-14"
IFS='-'
read -a datearr <<< "$data"

year=${datearr[0]}
month=${datearr[1]}
day=${datearr[2]}
from=${datearr[3]}
to=${datearr[4]}


printf "\n[Klient]: \n"
echo "data: $year-$month-$day, od godziny: $from do godziny: $to"


printf "\n[Serwer]: \n"
echo "Filmy ($year-$month-$day, od godziny: $from do godziny: $to)"
curl -s "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings" | jq -r '.'


echo
echo "Wybierz id filmu, który chcesz obejrzeć: "

printf "\n[Klient]: \n"

idScreening=3
echo "$idScreening"

printf "\n[Serwer]: \n"
curl -s "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats" | jq '.'

echo 
echo "Podaj imie, nazwisko i bilety jakie chcesz kupić (dorosły - 25zł, student - 18zł, dziecko - 12,50zł):"

printf "\n[Klient]: \n"
name="Łucja"
surname="Kossak-Szczucka"
dateOfBooking="2022-10-18 11:30"
tickets="5-student"
echo "$name $surname $tickets"

response=$(curl -s -X POST "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations" -H "Content-Type: application/json" -d ' { "name": "'"$name"'", "surname": "'"$surname"'", "dateOfBooking": "'"$dateOfBooking"'", "tickets": "'"$tickets"'" } ')

id=$(jq -r '.id' <<< $response)

printf "\n[Serwer]: \n"
echo "Twoja rezerwacja: "
curl -s "http:/localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations/$id" | jq '.'
echo

exit


