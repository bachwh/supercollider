
PlayerMixerGui : AbstractPlayerGui {

	guiBody { arg layout;
		this.durationGui(layout);
		model.players.do({ arg pl;
			layout.indent(1);
			pl.gui(layout.startRow);	
			layout.indent(-1);
		});
	}
	
}
