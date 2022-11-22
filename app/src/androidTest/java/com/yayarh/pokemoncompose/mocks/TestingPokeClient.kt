package com.yayarh.pokemoncompose.mocks

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import me.sargunvohra.lib.pokekotlin.client.*
import me.sargunvohra.lib.pokekotlin.model.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class TestingPokeClient(clientConfig: ClientConfig = ClientConfig()) : PokeApi {

    private val service = PokeApiServiceImpl(clientConfig)

    private fun <T> Call<T>.result(): T {
        return execute().let {
            if (it.isSuccessful) it.body()!! else throw ErrorResponse(it.code(), it.message())
        }
    }

    // region ResourceList Lists

    // region Berries

    override fun getBerryList(offset: Int, limit: Int) = service.getBerryList(offset, limit).result()

    override fun getBerryFirmnessList(offset: Int, limit: Int) = service.getBerryFirmnessList(offset, limit).result()

    override fun getBerryFlavorList(offset: Int, limit: Int) = service.getBerryFlavorList(offset, limit).result()

    // endregion Berries

    // region Contests

    override fun getContestTypeList(offset: Int, limit: Int) = service.getContestTypeList(offset, limit).result()

    override fun getContestEffectList(offset: Int, limit: Int) = service.getContestEffectList(offset, limit).result()

    override fun getSuperContestEffectList(offset: Int, limit: Int) = service.getSuperContestEffectList(offset, limit).result()

    // endregion Contests

    // region Encounters

    override fun getEncounterMethodList(offset: Int, limit: Int) = service.getEncounterMethodList(offset, limit).result()

    override fun getEncounterConditionList(offset: Int, limit: Int) = service.getEncounterConditionList(offset, limit).result()

    override fun getEncounterConditionValueList(offset: Int, limit: Int) = service.getEncounterConditionValueList(offset, limit).result()

    // endregion

    // region Evolution

    override fun getEvolutionChainList(offset: Int, limit: Int) = service.getEvolutionChainList(offset, limit).result()

    override fun getEvolutionTriggerList(offset: Int, limit: Int) = service.getEvolutionTriggerList(offset, limit).result()

    // endregion

    //region Games

    override fun getGenerationList(offset: Int, limit: Int) = service.getGenerationList(offset, limit).result()

    override fun getPokedexList(offset: Int, limit: Int) = service.getPokedexList(offset, limit).result()

    override fun getVersionList(offset: Int, limit: Int) = service.getVersionList(offset, limit).result()

    override fun getVersionGroupList(offset: Int, limit: Int) = service.getVersionGroupList(offset, limit).result()

    // endregion

    // region Items

    override fun getItemList(offset: Int, limit: Int) = service.getItemList(offset, limit).result()

    override fun getItemAttributeList(offset: Int, limit: Int) = service.getItemAttributeList(offset, limit).result()

    override fun getItemCategoryList(offset: Int, limit: Int) = service.getItemCategoryList(offset, limit).result()

    override fun getItemFlingEffectList(offset: Int, limit: Int) = service.getItemFlingEffectList(offset, limit).result()

    override fun getItemPocketList(offset: Int, limit: Int) = service.getItemPocketList(offset, limit).result()

    // endregion

    //region Moves

    override fun getMoveList(offset: Int, limit: Int) = service.getMoveList(offset, limit).result()

    override fun getMoveAilmentList(offset: Int, limit: Int) = service.getMoveAilmentList(offset, limit).result()

    override fun getMoveBattleStyleList(offset: Int, limit: Int) = service.getMoveBattleStyleList(offset, limit).result()

    override fun getMoveCategoryList(offset: Int, limit: Int) = service.getMoveCategoryList(offset, limit).result()

    override fun getMoveDamageClassList(offset: Int, limit: Int) = service.getMoveDamageClassList(offset, limit).result()

    override fun getMoveLearnMethodList(offset: Int, limit: Int) = service.getMoveLearnMethodList(offset, limit).result()

    override fun getMoveTargetList(offset: Int, limit: Int) = service.getMoveTargetList(offset, limit).result()

    // endregion

    // region Locations

    override fun getLocationList(offset: Int, limit: Int) = service.getLocationList(offset, limit).result()

    override fun getLocationAreaList(offset: Int, limit: Int) = service.getLocationAreaList(offset, limit).result()

    override fun getPalParkAreaList(offset: Int, limit: Int) = service.getPalParkAreaList(offset, limit).result()

    override fun getRegionList(offset: Int, limit: Int) = service.getRegionList(offset, limit).result()

    // endregion

    // region Pokemon

    override fun getAbilityList(offset: Int, limit: Int) = service.getAbilityList(offset, limit).result()

    override fun getCharacteristicList(offset: Int, limit: Int) = service.getCharacteristicList(offset, limit).result()

    override fun getEggGroupList(offset: Int, limit: Int) = service.getEggGroupList(offset, limit).result()

    override fun getGenderList(offset: Int, limit: Int) = service.getGenderList(offset, limit).result()

    override fun getGrowthRateList(offset: Int, limit: Int) = service.getGrowthRateList(offset, limit).result()

    override fun getNatureList(offset: Int, limit: Int) = service.getNatureList(offset, limit).result()

    override fun getPokeathlonStatList(offset: Int, limit: Int) = service.getPokeathlonStatList(offset, limit).result()

    override fun getPokemonList(offset: Int, limit: Int) = service.getPokemonList(offset, limit).result()

    override fun getPokemonColorList(offset: Int, limit: Int) = service.getPokemonColorList(offset, limit).result()

    override fun getPokemonFormList(offset: Int, limit: Int) = service.getPokemonFormList(offset, limit).result()

    override fun getPokemonHabitatList(offset: Int, limit: Int) = service.getPokemonHabitatList(offset, limit).result()

    override fun getPokemonShapeList(offset: Int, limit: Int) = service.getPokemonShapeList(offset, limit).result()

    override fun getPokemonSpeciesList(offset: Int, limit: Int) = service.getPokemonSpeciesList(offset, limit).result()

    override fun getStatList(offset: Int, limit: Int) = service.getStatList(offset, limit).result()

    override fun getTypeList(offset: Int, limit: Int) = service.getTypeList(offset, limit).result()

    // endregion

    // region Utility

    override fun getLanguageList(offset: Int, limit: Int) = service.getLanguageList(offset, limit).result()

    // endregion

    // endregion

    // region Berries

    override fun getBerry(id: Int) = service.getBerry(id).result()

    override fun getBerryFirmness(id: Int) = service.getBerryFirmness(id).result()

    override fun getBerryFlavor(id: Int) = service.getBerryFlavor(id).result()

    // endregion Berries

    // region Contests

    override fun getContestType(id: Int) = service.getContestType(id).result()

    override fun getContestEffect(id: Int) = service.getContestEffect(id).result()

    override fun getSuperContestEffect(id: Int) = service.getSuperContestEffect(id).result()

    // endregion Contests

    // region Encounters

    override fun getEncounterMethod(id: Int) = service.getEncounterMethod(id).result()

    override fun getEncounterCondition(id: Int) = service.getEncounterCondition(id).result()

    override fun getEncounterConditionValue(id: Int) = service.getEncounterConditionValue(id).result()

    // endregion Contests

    // region Evolution

    override fun getEvolutionChain(id: Int) = service.getEvolutionChain(id).result()

    override fun getEvolutionTrigger(id: Int) = service.getEvolutionTrigger(id).result()

    // endregion Evolution

    // region Games

    override fun getGeneration(id: Int) = service.getGeneration(id).result()

    override fun getPokedex(id: Int) = service.getPokedex(id).result()

    override fun getVersion(id: Int) = service.getVersion(id).result()

    override fun getVersionGroup(id: Int) = service.getVersionGroup(id).result()

    // endregion Games

    // region Items

    override fun getItem(id: Int) = service.getItem(id).result()

    override fun getItemAttribute(id: Int) = service.getItemAttribute(id).result()

    override fun getItemCategory(id: Int) = service.getItemCategory(id).result()

    override fun getItemFlingEffect(id: Int) = service.getItemFlingEffect(id).result()

    override fun getItemPocket(id: Int) = service.getItemPocket(id).result()

    // endregion Items

    // region Moves

    override fun getMove(id: Int) = service.getMove(id).result()

    override fun getMoveAilment(id: Int) = service.getMoveAilment(id).result()

    override fun getMoveBattleStyle(id: Int) = service.getMoveBattleStyle(id).result()

    override fun getMoveCategory(id: Int) = service.getMoveCategory(id).result()

    override fun getMoveDamageClass(id: Int) = service.getMoveDamageClass(id).result()

    override fun getMoveLearnMethod(id: Int) = service.getMoveLearnMethod(id).result()

    override fun getMoveTarget(id: Int) = service.getMoveTarget(id).result()

    // endregion Moves

    // region Locations

    override fun getLocation(id: Int) = service.getLocation(id).result()

    override fun getLocationArea(id: Int) = service.getLocationArea(id).result()

    override fun getPalParkArea(id: Int) = service.getPalParkArea(id).result()

    override fun getRegion(id: Int) = service.getRegion(id).result()

    // endregion Locations

    // region Pokemon

    override fun getAbility(id: Int) = service.getAbility(id).result()

    override fun getCharacteristic(id: Int) = service.getCharacteristic(id).result()

    override fun getEggGroup(id: Int) = service.getEggGroup(id).result()

    override fun getGender(id: Int) = service.getGender(id).result()

    override fun getGrowthRate(id: Int) = service.getGrowthRate(id).result()

    override fun getNature(id: Int) = service.getNature(id).result()

    override fun getPokeathlonStat(id: Int) = service.getPokeathlonStat(id).result()

    override fun getPokemon(id: Int): Pokemon {
        return Pokemon(
            id = 1,
            name = "Buu",
            moves = emptyList(),
            baseExperience = 50,
            gameIndices = emptyList(),
            height = 50,
            weight = 70,
            isDefault = true,
            order = 2,
            species = NamedApiResource(name = "Pokemon", category = "None", id = 5),
            heldItems = emptyList(),
            forms = emptyList(),
            sprites = PokemonSprites(
                null,
                null,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                null,
                null,
                null,
                null,
                null
            ),
            stats = listOf(
                PokemonStat(stat = NamedApiResource(name = "Health", id = 5, category = "stat"), effort = 1, baseStat = 40),
                PokemonStat(stat = NamedApiResource(name = "Attack", id = 6, category = "stat"), effort = 1, baseStat = 10),
                PokemonStat(stat = NamedApiResource(name = "Defense", id = 7, category = "stat"), effort = 1, baseStat = 7)
            ),
            abilities = listOf(
                PokemonAbility(false, 1, NamedApiResource(name = "Chidori", id = 1, category = "ability")),
                PokemonAbility(false, 2, NamedApiResource(name = "Thunderstorm", id = 2, category = "ability")),
                PokemonAbility(false, 3, NamedApiResource(name = "Fast boi", id = 3, category = "ability"))
            ),
            types = emptyList()
        )

    }

    override fun getPokemonEncounterList(id: Int) = service.getPokemonEncounterList(id).result()

    override fun getPokemonColor(id: Int) = service.getPokemonColor(id).result()

    override fun getPokemonForm(id: Int) = service.getPokemonForm(id).result()

    override fun getPokemonHabitat(id: Int) = service.getPokemonHabitat(id).result()

    override fun getPokemonShape(id: Int) = service.getPokemonShape(id).result()

    override fun getPokemonSpecies(id: Int) = service.getPokemonSpecies(id).result()

    override fun getStat(id: Int) = service.getStat(id).result()

    override fun getType(id: Int) = service.getType(id).result()

    // endregion Pokemon

    // region Utility

    override fun getLanguage(id: Int) = service.getLanguage(id).result()

    // endregion Utility

    inner class PokeApiServiceImpl(
        private val config: ClientConfig
    ) : PokeApiService by Retrofit.Builder()
        .baseUrl(config.rootUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().apply { setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) }.create()
            )
        )
        .client(OkHttpClient.Builder().(config.okHttpConfig)().build())
        .build()
        .create(PokeApiService::class.java)

    internal interface PokeApiService {

        // region Resource Lists

        // region Berries

        @GET("berry/")
        fun getBerryList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("berry-firmness/")
        fun getBerryFirmnessList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("berry-flavor/")
        fun getBerryFlavorList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion Berries

        // region Contests

        @GET("contest-type/")
        fun getContestTypeList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("contest-effect/")
        fun getContestEffectList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<ApiResourceList>

        @GET("super-contest-effect/")
        fun getSuperContestEffectList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<ApiResourceList>

        // endregion Contests

        // region Encounters

        @GET("encounter-method/")
        fun getEncounterMethodList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("encounter-condition/")
        fun getEncounterConditionList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("encounter-condition-value/")
        fun getEncounterConditionValueList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        // region Evolution

        @GET("evolution-chain/")
        fun getEvolutionChainList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<ApiResourceList>

        @GET("evolution-trigger/")
        fun getEvolutionTriggerList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        //region Games

        @GET("generation/")
        fun getGenerationList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokedex/")
        fun getPokedexList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("version/")
        fun getVersionList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("version-group/")
        fun getVersionGroupList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        // region Items

        @GET("item/")
        fun getItemList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("item-attribute/")
        fun getItemAttributeList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("item-category/")
        fun getItemCategoryList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("item-fling-effect/")
        fun getItemFlingEffectList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("item-pocket/")
        fun getItemPocketList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        //region Moves

        @GET("move/")
        fun getMoveList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("move-ailment/")
        fun getMoveAilmentList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("move-battle-style/")
        fun getMoveBattleStyleList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("move-category/")
        fun getMoveCategoryList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("move-damage-class/")
        fun getMoveDamageClassList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("move-learn-method/")
        fun getMoveLearnMethodList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("move-target/")
        fun getMoveTargetList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        // region Locations

        @GET("location/")
        fun getLocationList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("location-area/")
        fun getLocationAreaList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pal-park-area/")
        fun getPalParkAreaList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("region/")
        fun getRegionList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        // region Pokemon

        @GET("ability/")
        fun getAbilityList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("characteristic/")
        fun getCharacteristicList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<ApiResourceList>

        @GET("egg-group/")
        fun getEggGroupList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("gender/")
        fun getGenderList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("growth-rate/")
        fun getGrowthRateList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("nature/")
        fun getNatureList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokeathlon-stat/")
        fun getPokeathlonStatList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokemon/")
        fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokemon-color/")
        fun getPokemonColorList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokemon-form/")
        fun getPokemonFormList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokemon-habitat/")
        fun getPokemonHabitatList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokemon-shape/")
        fun getPokemonShapeList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("pokemon-species/")
        fun getPokemonSpeciesList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("stat/")
        fun getStatList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        @GET("type/")
        fun getTypeList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        // region Utility

        @GET("language/")
        fun getLanguageList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<NamedApiResourceList>

        // endregion

        // endregion

        // region Berries

        @GET("berry/{id}/")
        fun getBerry(@Path("id") id: Int): Call<Berry>

        @GET("berry-firmness/{id}/")
        fun getBerryFirmness(@Path("id") id: Int): Call<BerryFirmness>

        @GET("berry-flavor/{id}/")
        fun getBerryFlavor(@Path("id") id: Int): Call<BerryFlavor>

        // endregion Berries

        // region Contests

        @GET("contest-type/{id}/")
        fun getContestType(@Path("id") id: Int): Call<ContestType>

        @GET("contest-effect/{id}/")
        fun getContestEffect(@Path("id") id: Int): Call<ContestEffect>

        @GET("super-contest-effect/{id}/")
        fun getSuperContestEffect(@Path("id") id: Int): Call<SuperContestEffect>

        // endregion Contests

        // region Encounters

        @GET("encounter-method/{id}/")
        fun getEncounterMethod(@Path("id") id: Int): Call<EncounterMethod>

        @GET("encounter-condition/{id}/")
        fun getEncounterCondition(@Path("id") id: Int): Call<EncounterCondition>

        @GET("encounter-condition-value/{id}/")
        fun getEncounterConditionValue(@Path("id") id: Int): Call<EncounterConditionValue>

        // endregion Contests

        // region Evolution

        @GET("evolution-chain/{id}/")
        fun getEvolutionChain(@Path("id") id: Int): Call<EvolutionChain>

        @GET("evolution-trigger/{id}/")
        fun getEvolutionTrigger(@Path("id") id: Int): Call<EvolutionTrigger>

        // endregion Evolution

        // region Games

        @GET("generation/{id}/")
        fun getGeneration(@Path("id") id: Int): Call<Generation>

        @GET("pokedex/{id}/")
        fun getPokedex(@Path("id") id: Int): Call<Pokedex>

        @GET("version/{id}/")
        fun getVersion(@Path("id") id: Int): Call<Version>

        @GET("version-group/{id}/")
        fun getVersionGroup(@Path("id") id: Int): Call<VersionGroup>

        // endregion Games

        // region Items

        @GET("item/{id}/")
        fun getItem(@Path("id") id: Int): Call<Item>

        @GET("item-attribute/{id}/")
        fun getItemAttribute(@Path("id") id: Int): Call<ItemAttribute>

        @GET("item-category/{id}/")
        fun getItemCategory(@Path("id") id: Int): Call<ItemCategory>

        @GET("item-fling-effect/{id}/")
        fun getItemFlingEffect(@Path("id") id: Int): Call<ItemFlingEffect>

        @GET("item-pocket/{id}/")
        fun getItemPocket(@Path("id") id: Int): Call<ItemPocket>

        // endregion Items

        // region Moves

        @GET("move/{id}/")
        fun getMove(@Path("id") id: Int): Call<Move>

        @GET("move-ailment/{id}/")
        fun getMoveAilment(@Path("id") id: Int): Call<MoveAilment>

        @GET("move-battle-style/{id}/")
        fun getMoveBattleStyle(@Path("id") id: Int): Call<MoveBattleStyle>

        @GET("move-category/{id}/")
        fun getMoveCategory(@Path("id") id: Int): Call<MoveCategory>

        @GET("move-damage-class/{id}/")
        fun getMoveDamageClass(@Path("id") id: Int): Call<MoveDamageClass>

        @GET("move-learn-method/{id}/")
        fun getMoveLearnMethod(@Path("id") id: Int): Call<MoveLearnMethod>

        @GET("move-target/{id}/")
        fun getMoveTarget(@Path("id") id: Int): Call<MoveTarget>

        // endregion Moves

        // region Locations

        @GET("location/{id}/")
        fun getLocation(@Path("id") id: Int): Call<Location>

        @GET("location-area/{id}/")
        fun getLocationArea(@Path("id") id: Int): Call<LocationArea>

        @GET("pal-park-area/{id}/")
        fun getPalParkArea(@Path("id") id: Int): Call<PalParkArea>

        @GET("region/{id}/")
        fun getRegion(@Path("id") id: Int): Call<Region>

        // endregion Locations

        // region Pokemon

        @GET("ability/{id}/")
        fun getAbility(@Path("id") id: Int): Call<Ability>

        @GET("characteristic/{id}/")
        fun getCharacteristic(@Path("id") id: Int): Call<Characteristic>

        @GET("egg-group/{id}/")
        fun getEggGroup(@Path("id") id: Int): Call<EggGroup>

        @GET("gender/{id}/")
        fun getGender(@Path("id") id: Int): Call<Gender>

        @GET("growth-rate/{id}/")
        fun getGrowthRate(@Path("id") id: Int): Call<GrowthRate>

        @GET("nature/{id}/")
        fun getNature(@Path("id") id: Int): Call<Nature>

        @GET("pokeathlon-stat/{id}/")
        fun getPokeathlonStat(@Path("id") id: Int): Call<PokeathlonStat>

        @GET("pokemon/{id}/")
        fun getPokemon(@Path("id") id: Int): Call<Pokemon>

        @GET("pokemon/{id}/encounters/")
        fun getPokemonEncounterList(@Path("id") id: Int): Call<List<LocationAreaEncounter>>

        @GET("pokemon-color/{id}/")
        fun getPokemonColor(@Path("id") id: Int): Call<PokemonColor>

        @GET("pokemon-form/{id}/")
        fun getPokemonForm(@Path("id") id: Int): Call<PokemonForm>

        @GET("pokemon-habitat/{id}/")
        fun getPokemonHabitat(@Path("id") id: Int): Call<PokemonHabitat>

        @GET("pokemon-shape/{id}/")
        fun getPokemonShape(@Path("id") id: Int): Call<PokemonShape>

        @GET("pokemon-species/{id}/")
        fun getPokemonSpecies(@Path("id") id: Int): Call<PokemonSpecies>

        @GET("stat/{id}/")
        fun getStat(@Path("id") id: Int): Call<Stat>

        @GET("type/{id}/")
        fun getType(@Path("id") id: Int): Call<Type>

        // endregion Pokemon

        // region Utility

        @GET("language/{id}/")
        fun getLanguage(@Path("id") id: Int): Call<Language>

        // endregion Utility
    }


}