//jrh 2002, updated version of Pdict
//Plib / Plibn needs Parcel Lib

Pdict : Pattern {
	var  <>dict, <>which, <>repeats, <>default;
	*new { arg dict, which, repeats=inf, default;
		^super.new.dict_(dict).which_(which).repeats_(repeats).default_(default);
	}
	
	asStream {
		^Routine.new({ arg inval;
			var item, key, keyStream;
			keyStream = which.asStream;
			repeats.value.do({ 
				item = dict.at(keyStream.next);
				if(item.isNil, { 
						default.embedInStream(inval) 
					}, {
						item.embedInStream(inval);
				});
			})
		});
	}
	
}

Penvir : Pattern {
	var  <>which,  <>repeats, <>default;
	*new { arg  which, repeats=inf, default=0;
		^super.new.which_(which).repeats_(repeats).default_(default);
	}
	
	asStream {
		^Routine.new({ arg inval;
			var item, key, keyStream;
			keyStream = which.asStream;
			repeats.value.do({ 
				item = this.at(keyStream.next);
				if(item.isNil, { 
						 default.embedInStream(inval) 
					}, {
						item.embedInStream(inval);
				});
			})
		});
	}
	at { arg key;
		^currentEnvironment.at(key)
	}

}

Plib : Penvir {
	*new { arg  which, repeats=inf, default;
		^super.new(which, repeats, default ?? { this.default })
	}
	*default {
		"... ".post;
		 ^Pdef.at(\rest) 
	}
	at { arg key;
		^Pdef.at(key) //change that maybe.
	}
}

Plibn : Penvir {

	at { arg key;
		^Pdefn.at(key)
	}
	*default {
		 ^Pdefn.at(\rest) 
	}
}


