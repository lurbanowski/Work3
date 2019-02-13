# Work3
1. Aplikacja realizuje tylko odczyt danych z pliku XML i CSV i zapisuje do bazy MySQL według podanego wcześniej schematu.
2. Pliki zewnętrzne są ustawione na sztywno, poprzez wskazanie bezpośredniego adresu.
3. Stworzyłem dwie klasy Person i Contacts, odzielne klasy dla odczytu XML i CSV oraz dwie kalasy PersonDao, ContactsDao.
4. Program uruchamia klasa Main. 
5. Przechowywane kontakty rozpoznaję w przypadku XML po nazwie tagu xml, 
a w formacie CSV poprzez regex i jeden typ po długość wartości(jabber). 
6. Baza danych realizuje poniższy schemat
Tabela CUSTOMERS:
ID
NAME
SURNAME
Age (NULL)
Tablea CONTACTS:
ID
ID_CUSTOMER
TYPE (integer - 0 - unknown, 1 - email, 2 - phone, 3- jabber)
CONTACT

7. Informacja o mieście podawana w plikach, świadomie nie zoastała użyta do zapisu w bazie, gdyż trzymałem się podanego schematu.
