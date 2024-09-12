package org.fg.fom.persistence.model


import com.github.slugify.Slugify

class InvalidCharacterException(message: String) : IllegalArgumentException(message)

private val slugify = Slugify.builder().build()

data class FateOfMythrasCharacter(
    val name: String,
    val aspects: Map<AspectId, Aspect> = emptyMap(),
    val characteristics: Map<CharacteristicType, Characteristics> = emptyMap(),
    val skills: Map<SkillId, Skill> = emptyMap(),
    val stunts: Map<StuntId, Stunt> = emptyMap(),
    val fatePoints: Int
) {
    val id: String = slugify.slugify(name)
}

data class Aspect(
    val name: String,
    val type: AspectType
) {
    val id: String = slugify.slugify(name)
}

enum class AspectType {
    HIGH_CONCEPT, TROUBLE, CHARACTER, SITUATION, CONSEQUENCE
}

class Characteristics(val min: Int, val max: Int, val value: Int, val characteristicType: CharacteristicType)

typealias AspectId = String
typealias CharacteristicId = String
typealias SkillId = String
typealias StuntId = String

sealed class Skill(
    open val name: String,
    open val value: Int,
    open val primaryCharacteristic: CharacteristicType,
    open val secondaryCharacteristic: CharacteristicType,
) {


    val id: String = slugify.slugify(name)

    data class AspectTiedSkill(
        override val name: String,
        override val value: Int,
        override val primaryCharacteristic: CharacteristicType,
        override val secondaryCharacteristic: CharacteristicType,
        val aspect: Aspect,
    ) : Skill(name, value, primaryCharacteristic, secondaryCharacteristic)

    data class StandardSkill(
        override val name: String,
        override val value: Int,
        override val primaryCharacteristic: CharacteristicType,
        override val secondaryCharacteristic: CharacteristicType,
    ) : Skill(name, value, primaryCharacteristic, secondaryCharacteristic)
}

enum class CharacteristicType(val shortName: String, val longName: String) {
    STRENGTH("STR", "Strength"),
    CONSTITUTION("CON", "Constitution"),
    DEXTERITY("DEX", "Dexterity"),
    INTELLIGENCE("INT", "Intelligence"),
    POWER("POW", "Power"),
    CHARISMA("CHA", "Charisma"),
}

data class Stunt(
    val name: String,
    val description: String,
    val cost: StuntCost
) {
    val id: String = slugify.slugify(name)

}

enum class StuntCost {
    NONE, FATE_POINT, COOLDOWN, MIXED
}

// Derived attributes
data class DerivedAttributes(
    val actionPoints: Int,
    val damageModifier: String,
    val initiativeBonus: Int,
    val movementRate: Int
)



