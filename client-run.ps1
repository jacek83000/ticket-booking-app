Write-Host "*** START APLIKACJI ***"

if (Get-Command curl -errorAction SilentlyContinue)
{
    Write-Host "Znaleziono 'curl'." -ForegroundColor DarkGray
} else {
    Write-Host "Polecenie 'curl' jest wymagane do działania progamu." -ForegroundColor Red
    exit
}

if (Get-Command jq -errorAction SilentlyContinue)
{
    Write-Host "Znaleziono 'jq'." -ForegroundColor DarkGray
} else {
    Write-Host "Polecenie 'jq' jest wymagane do działania progamu." -ForegroundColor Red
    exit
}

# function convertFrom-MisinterpretedUtf8([string] $String) {
#   [System.Text.Encoding]::UTF8.GetString(
#      [System.Text.Encoding]::GetEncoding(28591).GetBytes($String)
#   )
# }


Write-Host "-----REZERWACJA-1---------------------------------------------------------------------------" -ForegroundColor Yellow
Write-Host "`n"
(Invoke-WebRequest "http://localhost:8080/app").Content

Write-Host "`n[Serwer]:" -ForegroundColor Green
Write-Host "Wybierz datę w jakiej chciałbyś/chciałabyś zobaczyć film (między 10:00 a 20:00):"
$data = "2022-10-18-12-16"
$splDate = $data.Split("-")
$year = $splDate[0]
$month = $splDate[1]
$day = $splDate[2]
$from = $splDate[3]
$to = $splDate[4]

Write-Host "`n[Klient]:" -ForegroundColor Cyan
Write-Host "data: $year-$month-$day, od godziny: $from do godziny: $to"

Write-Host "`n[Serwer]:" -ForegroundColor Green
Write-Host "Filmy ($year-$month-$day, od godziny: $from do godziny: $to): "
$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings"
(Invoke-WebRequest $uri).Content | jq -r '.'
Write-Host "`nWybierz id filmu, który chcesz obejrzeć:"

Write-Host "`n[Klient]:" -ForegroundColor Cyan
$idScreening=3
Write-Host $idScreening

Write-Host "`n[Serwer]:" -ForegroundColor Green
$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats"
(Invoke-WebRequest $uri).Content | jq '.'

Write-Host "Podaj imię, nazwisko i bilety jakie chcesz kupić (dorosły - 25zł, student - 18zł, dziecko - 12,50zł):"

Write-Host "`n[Klient]:" -ForegroundColor Cyan
$name = "Jan"
$surname = "Kowalski"
$dateOfBooking = "2022-10-18 10:00"
$tickets="2-dorosły 3-dziecko 4-student"
Write-Host $name $surname $tickets

$body = @{
    name           =$name
    surname        =$surname
    dateOfBooking  =$dateOfBooking
    tickets        =$tickets
} | ConvertTo-Json


$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations"
$response = Invoke-WebRequest $uri -Method Post -Body ([System.Text.Encoding]::UTF8.GetBytes($body)) -ContentType "application/json"
$convertedResponse=$response | ConvertFrom-Json
$id=$convertedResponse.id

Write-Host "`n[Serwer]:" -ForegroundColor Green
Write-Host "Twoja rezerwacja: "
$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations/$id"
(Invoke-WebRequest $uri).Content | jq '.'
Write-Host ""

Write-Host "-----REZERWACJA-2---------------------------------------------------------------------------" -ForegroundColor Yellow
Write-Host "`n"
(Invoke-WebRequest "http://localhost:8080/app").Content

Write-Host "`n[Serwer]:" -ForegroundColor Green
Write-Host "Wybierz datę w jakiej chciałbyś/chciałabyś zobaczyć film (między 10:00 a 20:00):"
$data = "2022-10-18-12-14"
$splDate = $data.Split("-")
$year = $splDate[0]
$month = $splDate[1]
$day = $splDate[2]
$from = $splDate[3]
$to = $splDate[4]

Write-Host "`n[Klient]:" -ForegroundColor Cyan
Write-Host "data: $year-$month-$day, od godziny: $from do godziny: $to"

Write-Host "`n[Serwer]:" -ForegroundColor Green
Write-Host "Filmy ($year-$month-$day, od godziny: $from do godziny: $to): "
$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings"
(Invoke-WebRequest $uri).Content | jq -r '.'
Write-Host "`nWybierz id filmu, który chcesz obejrzeć:"

Write-Host "`n[Klient]:" -ForegroundColor Cyan
$idScreening=3
Write-Host $idScreening

Write-Host "`n[Serwer]:" -ForegroundColor Green
$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats"
(Invoke-WebRequest $uri).Content | jq '.'
Write-Host "Podaj imię, nazwisko i bilety jakie chcesz kupić (dorosły - 25zł, student - 18zł, dziecko - 12,50zł):"

Write-Host "`n[Klient]:" -ForegroundColor Cyan
$name = "Łucja"
$surname = "Kossak-Szczucka"
$dateOfBooking = "2022-10-18 11:30"
$tickets="5-student"
Write-Host $name $surname $tickets

$body = @{
    name           =$name
    surname        =$surname
    dateOfBooking  =$dateOfBooking
    tickets        =$tickets
} | ConvertTo-Json


$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations"
$response = Invoke-WebRequest $uri -Method Post -Body ([System.Text.Encoding]::UTF8.GetBytes($body)) -ContentType "application/json"
$convertedResponse=$response | ConvertFrom-Json
$id=$convertedResponse.id

Write-Host "`n[Serwer]:" -ForegroundColor Green
Write-Host "Twoja rezerwacja: "
$uri = "http://localhost:8080/app/$year-$month-$day/between:$from&$to/screenings/$idScreening/seats/reservations/$id"
(Invoke-WebRequest $uri).Content | jq '.'
Write-Host ""

exit