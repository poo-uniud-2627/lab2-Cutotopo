// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
// Consegna 5, variante 2: la classe Livelli della consegna 3, con guadagnaVita in più. I contratti ci sono;
// i campi e i corpi dei metodi li scrivete voi (potete ripartire dalla vostra consegna 3).
package pong;

/**
 * Il gioco a livelli contro il giocatore automatico: a che livello si è e quante vite restano.
 * Si vince un livello vincendo una partita; perdendo una partita si perde una vita.
 */
public class Livelli {
    private int currentLevel;
    private int maxLevel;

    private int currentLives;
    private int maxLives;

    private Lato human;
    private boolean isFinished;

    /**
     * Un gioco nuovo.
     * Pretende: niente.
     * Promette: livello 1, con tutte le vite; il gioco non è completato né perso.
     * Lancia IllegalArgumentException se numeroLivelli o vite è minore di 1, o se umano è null.
     */
    public Livelli(int numeroLivelli, int vite, Lato umano) {
        if (numeroLivelli < 1) {
            throw new IllegalArgumentException("numeroLivelli must be greater than zero");
        }
        if (vite < 1) {
            throw new IllegalArgumentException("vite must be greater than zero");
        }
        if (umano == null) {
            throw new IllegalArgumentException("umano must not be null");
        }

        this.maxLevel = numeroLivelli;
        this.maxLives = vite;
        this.human = umano;

        this.ricomincia();
    }

    /** Il livello a cui si gioca, fra 1 e getNumeroLivelli(). */
    public int getLivello() {
        return this.currentLevel;
    }

    /** Quanti livelli ha il gioco. */
    public int getNumeroLivelli() {
        return this.maxLevel;
    }

    /** Le vite che restano, fra 0 e quelle iniziali. */
    public int getVite() {
        return this.currentLives;
    }

    /** Il lato su cui gioca la persona (l'altro è il giocatore automatico). */
    public Lato getUmano() {
        return this.human;
    }

    /** Vero se è stato vinto l'ultimo livello. */
    public boolean isCompletato() {
        return this.isFinished && this.currentLives != 0;
    }

    /** Vero se le vite sono finite. */
    public boolean isPerso() {
        return this.currentLives == 0;
    }

    /** Vero se il gioco è completato o perso. */
    public boolean isFinito() {
        return this.isFinished;
    }

    /**
     * Registra com'è finita una partita.
     * Pretende: niente.
     * Promette: se il gioco è finito (completato o perso), non cambia niente. Altrimenti:
     *           se ha vinto umano, si passa al livello dopo; se era l'ultimo, il gioco è completato
     *           e il livello resta l'ultimo; le vite non cambiano.
     *           Se ha vinto l'altro lato, una vita in meno e il livello non cambia;
     *           con l'ultima vita il gioco è perso.
     * Lancia IllegalArgumentException se vincitore è null, e non cambia niente.
     */
    public void partitaFinita(Lato vincitore) {
        if (vincitore == null) {
            throw new IllegalArgumentException("vincitore must not be null");
        }

        if (!this.isFinished) {
            if (vincitore == this.human) {
                if (this.currentLevel < this.maxLevel) {
                    this.currentLevel++;
                } else {
                    this.isFinished = true;
                }
            } else {
                this.currentLives--;

                if (this.currentLives == 0) {
                    this.isFinished = true;
                }
            }
        }
    }

    /**
     * Si ricomincia da capo.
     * Pretende: niente.
     * Promette: livello 1, con tutte le vite; il gioco non è completato né perso.
     */
    public void ricomincia() {
        this.currentLevel = 1;
        this.currentLives = this.maxLives;
        this.isFinished = false;
    }

    /**
     * Si riguadagna una vita (variante 2: con le stelle).
     * Pretende: niente.
     * Promette: se il gioco è finito (completato o perso), non cambia niente. Altrimenti una vita in più,
     *           ma mai più delle vite iniziali: con tutte le vite, non cambia niente. Il livello non cambia.
     */
    public void guadagnaVita() {
        if (!this.isFinito() && this.currentLives < this.maxLives) {
            this.currentLives++;
        }

    }
}
