<h1> Innlevering 3</h1>

I denne iterasjonen har vi utvidet funksjonaliteten i CleanE enda en gang. I den forrige iterasjonen kunne man kunne lage nye brukere ved å lage en oppgave. Det ble da implisitt laget en bruker. Dette funket fint, men vi tenkte at det kunne være lurt å ha muligheten for å lage brukere eksplisitt. Dette kunne være nyttig hvis man for eksempel allerede hadde en bruker som hadde opptjent poeng før man hadde startet å bruke denne applikasjonen (se brukerhistorie 3).

I denne iterasjonen har vi også implementert REST API. Her støttes følgende HTTP-forespørsler: POST, PUT, GET, DELETE. POST brukes når man skal legge til en ny bruker på serveren. PUT brukes når man skal legge til en ny oppgave til en av brukerene på serveren. GET brukes når man skal hente noe ned fra serveren. DELETE brukes når man skal slette en oppgave fra en av brukerne på serveren. Det er laget noen nye klasser for å støtte dette. I fxui modulen er dette CleanERemoteController.java, CleanERemoteApp.java og RemoteCleanEAccess.java. I rest modulen er dettte Application.java, CleanEService.java og ResponseController.java.

Vi har også gjort noen mindre endringer som å fjerne oppdateringsknappen fra poengtabellen, slik at den nå oppdateres og sorteres automatisk når man brukere blir lagt til og oppgaver utføres.
</br>
</br>
<h1>Diagrammer</h1>
<h2>Sekvensdiagram</h2>

<img src="../docs/prosjekt-images/sekvensdiagram_release3.png">

<h2>Klassediagram</h2>

<img src="../docs/prosjekt-images/klassediagram.png" >
<figcaption><b>Vi valgte å eksludere mange av metodene i klassene som tilhørte REST, fordi de ikke ga noe verdi til diagrammet. Det mest essensielle er med.</b></figcaption>

<h2>Pakkediagram</h2>

<img src="../docs/prosjekt-images/pakkediagram.png">

</br>

<u><h1>Viktig info!</u></h1>
Vi leverer sent på grunn av sykdom i gruppa. Flere av gruppemedlemmene har vært syke i varierende grad, og en har hatt streptokokker, noe som har gjort det krevende for oss å jobbe i like høyt tempo som forventet. Vi ber derfor om forståelse for at vi leverer sent.