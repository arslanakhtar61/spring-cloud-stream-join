package kafka.streams.join;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Producers {

	public static void main(String... args) {

		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class);

		List<KeyValue<String, Long>> userClicks = Arrays.asList(
				new KeyValue<>("alice", 13L),
				new KeyValue<>("bob", 4L),
				new KeyValue<>("chao", 25L),
				new KeyValue<>("bob", 19L),
				new KeyValue<>("dave", 56L),
				new KeyValue<>("eve", 78L),
				new KeyValue<>("alice", 40L),
				new KeyValue<>("fang", 99L)
		);

		DefaultKafkaProducerFactory<String, Long> pf = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<String, Long> template = new KafkaTemplate<>(pf, true);
		template.setDefaultTopic("user-clicks3");

		for (KeyValue<String,Long> keyValue : userClicks) {
			template.sendDefault(keyValue.key, keyValue.value);
		}

		List<KeyValue<String, String>> userRegions = Arrays.asList(
				new KeyValue<>("alice", "asia"),   /* Alice lived in Asia originally... */
				new KeyValue<>("bob", "americas"),
				new KeyValue<>("chao", "asia"),
				new KeyValue<>("dave", "europe"),
				new KeyValue<>("alice", "europe"), /* ...but moved to Europe some time later. */
				new KeyValue<>("eve", "americas"),
				new KeyValue<>("fang", "asia")
		);

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		DefaultKafkaProducerFactory<String, String> pf1 = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<String, String> template1 = new KafkaTemplate<>(pf1, true);
		template1.setDefaultTopic("user-regions");

		for (KeyValue<String,String> keyValue : userRegions) {
			template1.sendDefault(keyValue.key, keyValue.value);
		}

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		List<KeyValue<PersonKey, Person>> persons = Arrays.asList(
				new KeyValue<>(new PersonKey("JONH", "wICK", 1), new Person(34)),
				new KeyValue<>(new PersonKey("Harley", "valla", 2), new Person(42)),
		new KeyValue<>(new PersonKey("Mike", "PENCE", 3), new Person(23)),
				new KeyValue<>(new PersonKey("Ali", "Akbar", 4), new Person(53)),
				new KeyValue<>(new PersonKey("Arslan", "Akhtar", 5), new Person(53)),
				new KeyValue<>(new PersonKey("Will", "David", 6), new Person(13)),
				new KeyValue<>(new PersonKey("Beoionca", "Christ", 7), new Person(64))
				);

		DefaultKafkaProducerFactory<PersonKey, Person> personF = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<PersonKey, Person> templatePerson = new KafkaTemplate<>(personF, true);
		templatePerson.setDefaultTopic("person");

		for (KeyValue<PersonKey, Person> keyValue : persons) {
			templatePerson.sendDefault(keyValue.key, keyValue.value);
		}

		List<KeyValue<SchoolKey, School>> schools = Arrays.asList(
				new KeyValue<>(new SchoolKey("BMIA", "PK", "Islamabad", 1), new School("Sector F/8")),
				new KeyValue<>(new SchoolKey("CMII", "Hk", "Rawalpindi", 2), new School("Sector G/8")),
				new KeyValue<>(new SchoolKey("SCSV", "USA", "Lahore", 3), new School("Sector H/8")),
				new KeyValue<>(new SchoolKey("NVS", "SW", "Faisalbad", 4), new School("Sector J/8")),
				new KeyValue<>(new SchoolKey("SNVJ", "CH", "Shikarpur", 5), new School("Sector C/8")),
				new KeyValue<>(new SchoolKey("DBJ", "CN", "Talaqand", 6), new School("Sector Z/8")),
				new KeyValue<>(new SchoolKey("SCNJ", "SE", "Karachi", 7), new School("Sector S/8"))
		);

		DefaultKafkaProducerFactory<SchoolKey, School> schoolF = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<SchoolKey, School> templateSchool = new KafkaTemplate<>(schoolF, true);
		templateSchool.setDefaultTopic("school");

		for (KeyValue<SchoolKey, School> keyValue : schools) {
			templateSchool.sendDefault(keyValue.key, keyValue.value);
		}

	}

}
