package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository  {
    private List<Author> authors;

    public AuthorRepository() {
        authors = new ArrayList<Author>();
        authors.add(new Author(1000,"J.K","Rowling","Joanne Rowling was born on 31st July 1965 at Yate General Hospital near Bristol, and grew up in Gloucestershire in England and in Chepstow, Gwent, in south-east Wales.\n" +
                "\n" +
                "Her father, Peter, was an aircraft engineer at the Rolls Royce factory in Bristol and her mother, Anne, was a science technician in the Chemistry department at Wyedean Comprehensive, where Jo herself went to school. Anne was diagnosed with multiple sclerosis when Jo was a teenager and died in 1990, before the Harry Potter books were published.  Jo also has a younger sister, Di."));
        authors.add(new Author(1001,"William","Shakespeare","William Shakespeare was a renowned English poet, playwright, and actor born in 1564 in Stratford-upon-Avon. His birthday is most commonly celebrated on 23 April (see When was Shakespeare born), which is also believed to be the date he died in 1616."))
        ;
        authors.add(new Author(1002,"Victor","Hugo","Victor Hugo, (born February 26, 1802, Besançon, France—died May 22, 1885, Paris), poet, novelist, and dramatist who was the most important of the French Romantic writers. Though regarded in France as one of that country’s greatest poets, he is better known abroad for such novels as Notre-Dame de Paris (1831) and Les Misérables (1862)."));
        authors.add(new Author(1003,"Fyodor"," Dostoevsky"," Dostoyevsky, was a Russian novelist, short story writer, essayist and journalist. Numerous literary critics regard him as one of the greatest novelists in all of world literature, as many of his works are considered highly influential masterpieces."));
        authors.add(new Author(1004,"Vidoe","Podgorec","Vidoe Podgorec (Macedonian: Видое Подгорец; 8 June 1934 – 14 April 1997) was a Macedonian writer and poet. He was born in Kolešino near Strumica and lived in Skopje. He wrote for children and adults and published a great number of poems, stories and novels.\n" +
                "\n" +
                "One of his most famous novels is The White Gypsy (Белото циганче)."));
        authors.add(new Author(1005,"Petre M.","Andreevski","Petre M. Andreevski was born in 1934 in Sloeštica, Demir Hisar. He attended the faculty of Philosophy in Skopje, where he wrote his first poems and initially made his name as a poet. He later went on to write stories, plays and novels, including Пиреј (Pirey), considered one of the classics of Macedonian literature. He died in 2006."));
        authors.add(new Author(1006,"Dante","Alighieri","Dante Alighieri was born in the San Martino quarter of Florence.  Not much is known about his early life, but most scholars agree that he was born sometime in May or June of 1265.  He was the son of Alighiero di Bellincione Alighieri and Bella degli Abati.  Dante’s mother died when he was just six years old.\n" +
                "\n" +
                "Beatrice was Dante’s literary muse and inspiration for the Divine Comedy.  At the age of nine Dante met Beatrice for the first time and immediately fell in love with her.  He did not see her again until exactly nine years later in 1283. Although the two barely spoke during this second encounter, Dante’s love for her grew stronger.  Dante and Beatrice never married.  In 1285 Dante married Gemma Donati and together they had four children: Giovanni, Pietro, Jacopo, and Antonia.  Beatrice became the wife of Simone dei Bardi, a prominent Florentine banker. "));
        authors.add(new Author(1007,"Miguel","De Cervantes","Miguel de Cervantes Saavedra (Spanish: [miˈɣel de θeɾˈβantes saaˈβeðɾa]; 29 September 1547 (assumed) – 22 April 1616 NS)[5] was an Early Modern Spanish writer widely regarded as the greatest writer in the Spanish language and one of the world's pre-eminent novelists. He is best known for his novel Don Quixote, a work often cited as both the first modern novel[6][7] and \"the first great novel of world literature\".[8] A 2002 poll of around 100 well-known authors[b] voted it the \"most meaningful book of all time\",[9] from among the \"best and most central works in world literature\".[8]"));
        authors.add(new Author(1008,"Stephen","King","Stephen King, (born September 21, 1947, Portland, Maine, U.S.), American novelist and short-story writer whose books are credited with reviving the genre of horror fiction in the late 20th century."));
        authors.add(new Author(1009,"Leo","Tolstoy","Count Lev Nikolayevich Tolstoy[note 1] (/ˈtoʊlstɔɪ, ˈtɒl-/;[1] Russian: Лев Николаевич Толстой,[note 2] IPA: [ˈlʲef nʲɪkɐˈla(j)ɪvʲɪtɕ tɐlˈstoj] ⓘ; 9 September [O.S. 28 August] 1828 – 20 November [O.S. 7 November] 1910), usually referred to in English as Leo Tolstoy, was a Russian writer regarded as one of the greatest authors of all time.[2] He received nominations for the Nobel Prize in Literature every year from 1902 to 1906 and for the Nobel Peace Prize in 1901, 1902, and 1909."));

    }
    public List<Author> findAll(){
        return authors;
    }

    public Author findById(Long id) {
        return (authors.stream().filter(author -> author.matchingID(id)).findFirst()
                .orElse(null));
    }
}

