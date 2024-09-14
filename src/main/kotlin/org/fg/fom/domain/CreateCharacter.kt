package org.fg.fom.domain

import org.fg.fom.persistence.model.CharacteristicType
import org.fg.fom.persistence.model.Characteristics
import org.fg.fom.persistence.model.Skill.StandardSkill

class CreateCharacter() {

    private fun initCharacteristics(): Map<CharacteristicType, Characteristics> =
        CharacteristicType.entries.map { type ->
            Characteristics(3, 30, 3, type)
        }.associate { it.characteristicType to it }

    fun initStandardSkills(): Map<String, StandardSkill> {
        return listOf(
            StandardSkill(
                name = "Athletics",
                primaryCharacteristic = CharacteristicType.STRENGTH,
                secondaryCharacteristic = CharacteristicType.DEXTERITY,
                value = 0
            ),
            StandardSkill(
                name = "Brawn",
                primaryCharacteristic = CharacteristicType.STRENGTH,
                secondaryCharacteristic = CharacteristicType.CONSTITUTION,
                value = 0
            ),
            StandardSkill(
                name = "Contacts",
                primaryCharacteristic = CharacteristicType.CHARISMA,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "Conceal",
                primaryCharacteristic = CharacteristicType.DEXTERITY,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "Customs",
                primaryCharacteristic = CharacteristicType.CHARISMA,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "Dance",
                primaryCharacteristic = CharacteristicType.DEXTERITY,
                secondaryCharacteristic = CharacteristicType.CHARISMA,
                value = 0
            ),
            StandardSkill(
                name = "Deceit",
                primaryCharacteristic = CharacteristicType.CHARISMA,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "Drive",
                primaryCharacteristic = CharacteristicType.DEXTERITY,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "Endurance",
                primaryCharacteristic = CharacteristicType.CONSTITUTION,
                secondaryCharacteristic = CharacteristicType.STRENGTH,
                value = 0
            ),
            StandardSkill(
                name = "Evade",
                primaryCharacteristic = CharacteristicType.DEXTERITY,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "First Aid",
                primaryCharacteristic = CharacteristicType.INTELLIGENCE,
                secondaryCharacteristic = CharacteristicType.DEXTERITY,
                value = 0
            ),
            StandardSkill(
                name = "Influence",
                primaryCharacteristic = CharacteristicType.CHARISMA,
                secondaryCharacteristic = CharacteristicType.POWER,
                value = 0
            ),
            StandardSkill(
                name = "Insight",
                primaryCharacteristic = CharacteristicType.POWER,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "Locale",
                primaryCharacteristic = CharacteristicType.INTELLIGENCE,
                secondaryCharacteristic = CharacteristicType.CHARISMA,
                value = 0
            ),
            StandardSkill(
                name = "Perception",
                primaryCharacteristic = CharacteristicType.INTELLIGENCE,
                secondaryCharacteristic = CharacteristicType.POWER,
                value = 0
            ),
            StandardSkill(
                name = "Ride",
                primaryCharacteristic = CharacteristicType.DEXTERITY,
                secondaryCharacteristic = CharacteristicType.POWER,
                value = 0
            ),
            StandardSkill(
                name = "Sing",
                primaryCharacteristic = CharacteristicType.CHARISMA,
                secondaryCharacteristic = CharacteristicType.CONSTITUTION,
                value = 0
            ),
            StandardSkill(
                name = "Stealth",
                primaryCharacteristic = CharacteristicType.DEXTERITY,
                secondaryCharacteristic = CharacteristicType.INTELLIGENCE,
                value = 0
            ),
            StandardSkill(
                name = "Swim",
                primaryCharacteristic = CharacteristicType.STRENGTH,
                secondaryCharacteristic = CharacteristicType.CONSTITUTION,
                value = 0
            ),
            StandardSkill(
                name = "Unarmed",
                primaryCharacteristic = CharacteristicType.STRENGTH,
                secondaryCharacteristic = CharacteristicType.DEXTERITY,
                value = 0
            ),
            StandardSkill(
                name = "Willpower",
                primaryCharacteristic = CharacteristicType.POWER,
                secondaryCharacteristic = CharacteristicType.CONSTITUTION,
                value = 0
            )
        ).associate { it.id to it }
    }
}