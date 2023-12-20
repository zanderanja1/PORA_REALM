# Realm
## Zakaj
[Realm](https://github.com/realm/realm-kotlin) je knjiznica ki omogoca shranjevanje objektov v podatkovno bazo, objekte lahko med seboj predstavimo z relacijami. Prav tako omogoca realnocasovno sinhronizacijo med napravami z mongoDB atlas, lahko pa zazenemo podatkovno bazo le lokalno na napravi. V primerjavi z SQLite sama datoteka podatkov zavzame manj prostora.

## Prednosti
- Baza je objektna podatkovna baza kar pomeni da je preprosta integracija z kotlin ali java objekti saj lahko kotlin objekt shranimo v podatkovno bazo namesto da bi se ukvarjali z neposrednimi SQL poizvedbami
- Omogoca relacije med objekti
- Resitev je na voljo tudi na drugih platformah kot na primer IOS
- Podatkovno bazo lahko uporabljamo brez povezave in nato sinhroniziramo z online podatkovno bazo
- Aktivna skupnost
## Slabosti
- Knjiznica Realm pripomore k vecji velikosti aplikacije kot SQLite
- Ker aplikacija nalozi celoten objekt v pomnilnik je izvedba hitrejsa vendar  lahko upocasni delovanje aplikacije
- Poizvedbe niso tako natance in bogate kot v SQL
- Resitev se razlikuje od drugih resitev zato je mogoce uporaba tezja
- Resitev je novejsa

## Licenca
Apache 2.0

## Stevilo uporabnikov
Do decembra 2023 je Realm na GitHubu med kotlin in java razlicicami oznacilo vec kot 65.000 uporabnikov. Na tisoce aplikacij uporablja resitev vkljucno z podjetji kot so Atlassianm, imgur, 7eleven...

## Vzdrževanje projekta
Realm aktivno vzdržuje MongoDB, zadnji commit je bil narejen 15.12.2023.

## Primer uporabe
### Vključitev v projekt
Build.gradle: project
```kotlin
plugins {
    id ("io.realm.kotlin") version "1.11.0" apply false
}
```
Build.gradle: app
```kotlin
plugins {
    id("io.realm.kotlin")
}
dependencies {
    implementation("io.realm.kotlin:library-base:1.11.0")
    implementation("io.realm.kotlin:library-sync:1.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}
```
Primer razreda Reminder ki deduje razred Address.
Pomebno je da razred deduje RealmObject.
Prav tako lahko dodamo oznake kot npr. @PrimaryKey
```kotlin
class Reminder: RealmObject {
    var time: String = ""
    var message: String = ""
    var date: String =""
    var location: Address? = null
}
```
Razred Address
```kotlin
class Address : RealmObject{
    var streetName: String = ""
    var postalCode: Int = 0
    var country: String = ""
}
```
Ustvarjanje sheme
```kotlin
val configuration = RealmConfiguration.create(schema = setOf( Reminder::class, Address::class))
val realm = Realm.open(configuration)
```
Ustarjanje instance razredov
```kotlin
val reminder = Reminder().apply {
            time = "12.00"
            message = "Zobozdravnik"
            date = sqlDate.toString()
            location = Address().apply { streetName = "Gradnikova 1"; postalCode = 5290; country ="Slovenia"}
        }
```

Za shranitev v bazo klicemo
```kotlin
realm.writeBlocking { 
            val managedReminder = copyToRealm(reminder)
        }
```
Primer poizvedbe
Poizvedbe so podobne kot pri MongoDB podatkovni bazi
```kotlin
val all = realm.query<Reminder>().find()
        val reminderByMessage: RealmQuery<Reminder> = realm.query<Reminder>("message = $1", "Zobozdravnik")
        val filteredByMessage: RealmResults<Reminder> = reminderByMessage.find()
```
### Ostale funkcionalnosti
- lastnosti v bazi lahko posodobimo in brisemo
- vsako shemo lahko globalno opazujemo za spremembe, enako se da lahko samo za posamezen objekt
- podobno kot v MongoDB imamo tudi Query ali pa singleQuery
- v realmList lahko shranimo podatkovne strukture

## Avtor
Žan Deranja
 

