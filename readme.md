<h1 align="center">Qook</h1>

<p align="center">

<img src="https://img.shields.io/badge/picasso-2.4.0-darkred" />
<img src="https://img.shields.io/badge/retrofit-2.9.0-blue" />
<img src="https://img.shields.io/badge/gson-2.8.6-red" />

<br/>

Qook is a simple app for storing recipes. You can choose what recipes do you want to cook today and generate shopping list based on that. Each recipe contains instructions, ingredients and steps required followed by picture of that recipe. 

App is written in kotlin and connects to spring boot [backend](https://github.com/Wokstym/QookBackend) using retrofit and shows pictures with help of piccasso, which are stored in external [image4.io](https://image4.io) service. Backend uses spring data jpa to store data in postgres, all managed by docker containers.

Currently app is hobby app tinkered with in free time to learn and preserve good practises aswell as build something usefull and pleasant to look at 

## Demo

<p align="center">
<img src="res/presentation.gif" alt="" data-canonical-src="res/presentation.gif" width="37.5%" height="37.5%" />
</p>
